package com.example.ui.coloring

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ColoringPage
import com.example.data.model.CrayonColor
import com.example.data.model.PredefinedPalettes
import com.example.data.model.SavedArtwork
import com.example.data.model.ToolType
import com.example.data.repository.ArtworkRepository
import com.example.data.repository.ColoringRepository
import com.example.data.repository.RewardsRepository
import com.example.domain.coloring.ColoringPageTemplates
import com.example.domain.coloring.ColoringProgressCalculator
import com.example.domain.coloring.FloodFill
import com.example.utils.SoundManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ColoringStudioUiState(
    val page: ColoringPage? = null,
    val selectedTool: ToolType = ToolType.FILL_BUCKET,
    val selectedColor: CrayonColor = PredefinedPalettes.primaryCrayons[0], // Sunny Red
    val brushSize: Float = 28f,
    val canUndo: Boolean = false,
    val canRedo: Boolean = false,
    val isSaving: Boolean = false,
    val isCelebrationVisible: Boolean = false,
    val savedArtwork: SavedArtwork? = null,
    val starsAwarded: Int = 0,
    val completionPercent: Int = 0,
    val isLowCompletion: Boolean = false,
    val coloringProgressPercent: Int = 0,
    val currentPaletteIndex: Int = 0 // 0: Classic, 1: Pastel, 2: Sparkle
)

class ColoringStudioViewModel(
    application: Application,
    private val coloringRepository: ColoringRepository,
    private val artworkRepository: ArtworkRepository,
    private val rewardsRepository: RewardsRepository,
    val soundManager: SoundManager
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ColoringStudioUiState())
    val uiState: StateFlow<ColoringStudioUiState> = _uiState.asStateFlow()

    // Working mutable Bitmap
    var canvasBitmap: Bitmap? = null
        private set

    // Clean original line-art bitmap for clear / template base
    private var baseOutlineBitmap: Bitmap? = null

    // Precomputed mask for rapid, lag-free progress checking
    private var colorableMask: ColoringProgressCalculator.ColorableMask? = null

    // Undo & Redo bitmap history stacks
    private val undoStack = mutableListOf<Bitmap>()
    private val redoStack = mutableListOf<Bitmap>()
    private val maxHistory = 10

    // Stars awarded during this coloring session to prevent duplicate rewards on repeated Complete presses
    private var sessionStarsAwarded: Int = 0

    // Background job for live progress calculation
    private var progressJob: Job? = null

    // Canvas drawing paint
    private val brushPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var drawingCanvas: Canvas? = null

    fun initializeStudio(page: ColoringPage, preloadedBitmap: Bitmap? = null) {
        viewModelScope.launch {
            sessionStarsAwarded = 0
            _uiState.update {
                it.copy(
                    page = page,
                    starsAwarded = 0,
                    completionPercent = 0,
                    coloringProgressPercent = 0,
                    isCelebrationVisible = false
                )
            }
            coloringRepository.recordRecentPage(page)

            withContext(Dispatchers.Default) {
                val lineArt = preloadedBitmap ?: ColoringPageTemplates.createTemplateBitmap(page.templateId)
                baseOutlineBitmap = lineArt.copy(Bitmap.Config.ARGB_8888, false)

                val workingCopy = lineArt.copy(Bitmap.Config.ARGB_8888, true)
                canvasBitmap = workingCopy
                drawingCanvas = Canvas(workingCopy)

                // Build colorable mask once for fast live evaluations
                val mask = ColoringProgressCalculator.buildColorableMask(lineArt, step = 4)
                colorableMask = mask

                undoStack.clear()
                redoStack.clear()
                saveStateToUndo(workingCopy)

                val initialProgress = ColoringProgressCalculator.evaluateProgress(mask, workingCopy)
                _uiState.update { it.copy(coloringProgressPercent = initialProgress) }
            }

            updateUndoRedoState()
        }
    }

    fun selectTool(tool: ToolType) {
        soundManager.playTap()
        _uiState.update { it.copy(selectedTool = tool) }
    }

    fun selectColor(color: CrayonColor) {
        soundManager.playTap()
        _uiState.update { it.copy(selectedColor = color) }
    }

    fun setPaletteIndex(index: Int) {
        soundManager.playTap()
        _uiState.update { it.copy(currentPaletteIndex = index) }
    }

    fun setBrushSize(size: Float) {
        soundManager.playTap()
        _uiState.update { it.copy(brushSize = size) }
    }

    private fun saveStateToUndo(current: Bitmap) {
        val snapshot = current.copy(Bitmap.Config.ARGB_8888, false)
        undoStack.add(snapshot)
        if (undoStack.size > maxHistory) {
            undoStack.removeAt(0)
        }
        redoStack.clear()
        updateUndoRedoState()
    }

    fun onTouchDown(x: Float, y: Float) {
        val bmp = canvasBitmap ?: return
        if (x < 0 || y < 0 || x >= bmp.width || y >= bmp.height) return

        when (_uiState.value.selectedTool) {
            ToolType.FILL_BUCKET -> {
                performFloodFill(x.toInt(), y.toInt())
            }
            ToolType.BRUSH, ToolType.CRAYON, ToolType.ERASER -> {
                saveStateToUndo(bmp)
            }
        }
    }

    fun onStrokeSegment(startX: Float, startY: Float, endX: Float, endY: Float) {
        val canvas = drawingCanvas ?: return
        val tool = _uiState.value.selectedTool
        if (tool == ToolType.FILL_BUCKET) return

        val colorInt = if (tool == ToolType.ERASER) {
            Color.WHITE
        } else {
            _uiState.value.selectedColor.colorInt
        }

        brushPaint.color = colorInt
        brushPaint.strokeWidth = when (tool) {
            ToolType.ERASER -> _uiState.value.brushSize * 1.5f
            ToolType.CRAYON -> _uiState.value.brushSize * 1.2f
            else -> _uiState.value.brushSize
        }

        if (tool == ToolType.CRAYON) {
            brushPaint.alpha = 230 // Crayon texture feel
        } else {
            brushPaint.alpha = 255
        }

        canvas.drawLine(startX, startY, endX, endY, brushPaint)
    }

    fun onStrokeEnd() {
        triggerProgressEvaluation()
    }

    private fun performFloodFill(x: Int, y: Int) {
        val bmp = canvasBitmap ?: return
        val targetColor = _uiState.value.selectedColor.colorInt

        viewModelScope.launch(Dispatchers.Default) {
            saveStateToUndo(bmp)
            val filled = FloodFill.fill(
                bitmap = bmp,
                startX = x,
                startY = y,
                fillColorInt = targetColor,
                tolerance = 38
            )
            if (filled) {
                soundManager.playFill()
                triggerProgressEvaluation()
            }
        }
    }

    fun undo() {
        if (undoStack.size > 1) {
            val current = undoStack.removeAt(undoStack.lastIndex)
            redoStack.add(current)

            val previous = undoStack.last()
            canvasBitmap?.let { bmp ->
                val canvas = Canvas(bmp)
                canvas.drawBitmap(previous, 0f, 0f, null)
            }
            soundManager.playUndo()
            updateUndoRedoState()
            triggerProgressEvaluation()
        }
    }

    fun redo() {
        if (redoStack.isNotEmpty()) {
            val next = redoStack.removeAt(redoStack.lastIndex)
            undoStack.add(next)

            canvasBitmap?.let { bmp ->
                val canvas = Canvas(bmp)
                canvas.drawBitmap(next, 0f, 0f, null)
            }
            soundManager.playTap()
            updateUndoRedoState()
            triggerProgressEvaluation()
        }
    }

    fun clearCanvas() {
        val bmp = canvasBitmap ?: return
        val base = baseOutlineBitmap ?: return

        saveStateToUndo(bmp)
        val canvas = Canvas(bmp)
        canvas.drawBitmap(base, 0f, 0f, null)
        soundManager.playUndo()
        updateUndoRedoState()
        triggerProgressEvaluation()
    }

    private fun updateUndoRedoState() {
        _uiState.update {
            it.copy(
                canUndo = undoStack.size > 1,
                canRedo = redoStack.isNotEmpty()
            )
        }
    }

    /**
     * Rapid debounced progress evaluation keeping the UI thread at 60fps.
     */
    private fun triggerProgressEvaluation() {
        val mask = colorableMask ?: return
        val bmp = canvasBitmap ?: return

        progressJob?.cancel()
        progressJob = viewModelScope.launch(Dispatchers.Default) {
            val progress = ColoringProgressCalculator.evaluateProgress(mask, bmp)
            _uiState.update { it.copy(coloringProgressPercent = progress) }
        }
    }

    /**
     * Accurately scores the coloring completion and awards stars based on exact user thresholds:
     * 0–19% = 0 stars
     * 20–39% = 1 star
     * 40–59% = 2 stars
     * 60–74% = 3 stars
     * 75–89% = 4 stars
     * 90–100% = 5 stars
     * Prevents repeated star duplication on multiple clicks.
     */
    fun completeAndSaveArtwork() {
        val bmp = canvasBitmap ?: return
        val page = _uiState.value.page ?: return
        val base = baseOutlineBitmap ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            val completionPercent = withContext(Dispatchers.Default) {
                ColoringProgressCalculator.calculateColoringCompletionPercent(base, bmp)
            }

            val earnedStars = ColoringProgressCalculator.calculateStars(completionPercent)

            // Award only newly earned stars to prevent repeated addition if pressed multiple times
            val starsToAdd = maxOf(0, earnedStars - sessionStarsAwarded)
            if (starsToAdd > 0) {
                rewardsRepository.addStars(starsToAdd)
            }
            sessionStarsAwarded = maxOf(sessionStarsAwarded, earnedStars)

            val saved = artworkRepository.saveArtwork(
                title = page.title,
                categoryId = page.category.id,
                templateId = page.id,
                bitmap = bmp,
                isCompleted = true,
                starsAwarded = sessionStarsAwarded
            )

            if (earnedStars > 0) {
                soundManager.playCelebration()
            } else {
                soundManager.playTap()
            }

            _uiState.update {
                it.copy(
                    isSaving = false,
                    isCelebrationVisible = true,
                    savedArtwork = saved,
                    starsAwarded = earnedStars,
                    completionPercent = completionPercent,
                    isLowCompletion = completionPercent < 20,
                    coloringProgressPercent = completionPercent
                )
            }
        }
    }

    fun dismissCelebration() {
        _uiState.update { it.copy(isCelebrationVisible = false) }
    }
}
