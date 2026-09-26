package com.example.ui.coloring

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CrayonColor
import com.example.data.model.PredefinedPalettes
import com.example.data.model.SavedArtwork
import com.example.data.model.ToolType
import com.example.ui.components.ConfettiCelebration
import com.example.ui.components.KidButton
import com.example.ui.theme.*
import com.example.utils.ImageStorageManager

@Composable
fun ColoringStudioScreen(
    viewModel: ColoringStudioViewModel,
    onBackClick: () -> Unit,
    onViewGallery: () -> Unit,
    onColorAnother: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Recomposition ticker for Canvas drawing updates
    var canvasVersion by remember { mutableStateOf(0) }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    var showClearConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StudioTopBar(
                title = uiState.page?.title ?: "Coloring Studio",
                progressPercent = uiState.coloringProgressPercent,
                canUndo = uiState.canUndo,
                canRedo = uiState.canRedo,
                onBack = onBackClick,
                onUndo = {
                    viewModel.undo()
                    canvasVersion++
                },
                onRedo = {
                    viewModel.redo()
                    canvasVersion++
                },
                onClear = { showClearConfirmDialog = true },
                onDone = { viewModel.completeAndSaveArtwork() }
            )
        },
        containerColor = Color(0xFFF6F7F9)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Main Interactive Canvas
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    shadowElevation = 6.dp,
                    border = androidx.compose.foundation.BorderStroke(3.dp, Color(0x22000000)),
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .testTag("coloring_canvas_surface")
                ) {
                    val bitmap = viewModel.canvasBitmap
                    var lastTouchPoint by remember { mutableStateOf<Offset?>(null) }

                    if (bitmap != null) {
                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(24.dp))
                                .onGloballyPositioned { coordinates ->
                                    canvasSize = coordinates.size
                                }
                                .pointerInput(uiState.selectedTool, uiState.selectedColor, uiState.brushSize) {
                                    detectTapGestures { offset ->
                                        if (canvasSize.width > 0 && canvasSize.height > 0) {
                                            val scaleX = bitmap.width.toFloat() / canvasSize.width
                                            val scaleY = bitmap.height.toFloat() / canvasSize.height
                                            viewModel.onTouchDown(offset.x * scaleX, offset.y * scaleY)
                                            viewModel.onStrokeEnd()
                                            canvasVersion++
                                        }
                                    }
                                }
                                .pointerInput(uiState.selectedTool, uiState.selectedColor, uiState.brushSize) {
                                    detectDragGestures(
                                        onDragStart = { offset ->
                                            if (canvasSize.width > 0 && canvasSize.height > 0) {
                                                val scaleX = bitmap.width.toFloat() / canvasSize.width
                                                val scaleY = bitmap.height.toFloat() / canvasSize.height
                                                val mapped = Offset(offset.x * scaleX, offset.y * scaleY)
                                                lastTouchPoint = mapped
                                                viewModel.onTouchDown(mapped.x, mapped.y)
                                                canvasVersion++
                                            }
                                        },
                                        onDrag = { change, _ ->
                                            change.consume()
                                            if (canvasSize.width > 0 && canvasSize.height > 0) {
                                                val scaleX = bitmap.width.toFloat() / canvasSize.width
                                                val scaleY = bitmap.height.toFloat() / canvasSize.height
                                                val currentMapped = Offset(change.position.x * scaleX, change.position.y * scaleY)
                                                val prev = lastTouchPoint ?: currentMapped
                                                viewModel.onStrokeSegment(prev.x, prev.y, currentMapped.x, currentMapped.y)
                                                lastTouchPoint = currentMapped
                                                canvasVersion++
                                            }
                                        },
                                        onDragEnd = {
                                            lastTouchPoint = null
                                            viewModel.onStrokeEnd()
                                            canvasVersion++
                                        },
                                        onDragCancel = {
                                            lastTouchPoint = null
                                            viewModel.onStrokeEnd()
                                        }
                                    )
                                }
                        ) {
                            // Read canvasVersion to ensure recomposition
                            @Suppress("UNUSED_VARIABLE")
                            val version = canvasVersion

                            drawImage(
                                image = bitmap.asImageBitmap(),
                                dstSize = IntSize(size.width.toInt(), size.height.toInt())
                            )
                        }
                    } else {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = WarmOrange)
                        }
                    }
                }
            }

            // Bottom Tools & Palette Panel
            StudioControlPanel(
                selectedTool = uiState.selectedTool,
                selectedColor = uiState.selectedColor,
                currentPaletteIndex = uiState.currentPaletteIndex,
                brushSize = uiState.brushSize,
                onSelectTool = viewModel::selectTool,
                onSelectColor = viewModel::selectColor,
                onSelectPaletteIndex = viewModel::setPaletteIndex,
                onSelectBrushSize = viewModel::setBrushSize
            )
        }

        // Clear Confirmation Dialog
        if (showClearConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showClearConfirmDialog = false },
                title = { Text(text = "Start Over? 🧼", fontWeight = FontWeight.Bold) },
                text = { Text("Do you want to wipe this drawing clean and start coloring again?") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.clearCanvas()
                            canvasVersion++
                            showClearConfirmDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CoralRed),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Yes, Clear")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showClearConfirmDialog = false },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Cancel")
                    }
                },
                shape = RoundedCornerShape(24.dp)
            )
        }

        // Celebration Dialog Overlay
        AnimatedVisibility(
            visible = uiState.isCelebrationVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CelebrationOverlay(
                artwork = uiState.savedArtwork,
                stars = uiState.starsAwarded,
                completionPercent = uiState.completionPercent,
                isLowCompletion = uiState.isLowCompletion,
                onShare = {
                    uiState.savedArtwork?.let { art ->
                        val shareIntent = ImageStorageManager.createShareIntent(
                            context = context,
                            filePath = art.imageFilePath,
                            title = art.title
                        )
                        if (shareIntent != null) {
                            context.startActivity(shareIntent)
                        }
                    }
                },
                onViewGallery = {
                    viewModel.dismissCelebration()
                    onViewGallery()
                },
                onColorAnother = {
                    viewModel.dismissCelebration()
                    onColorAnother()
                }
            )
        }
    }
}

@Composable
private fun StudioTopBar(
    title: String,
    progressPercent: Int,
    canUndo: Boolean,
    canRedo: Boolean,
    onBack: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onClear: () -> Unit,
    onDone: () -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f, fill = false)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF2F3F5))
                        .testTag("studio_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimaryDark
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        ),
                        color = TextPrimaryDark,
                        maxLines = 1
                    )

                    // Live coloring progress badge
                    Text(
                        text = "🎨 $progressPercent% Colored",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        color = if (progressPercent >= 20) GrassGreen else TextSecondaryDark
                    )
                }
            }

            // Studio Actions: Undo, Redo, Clear, Done
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(
                    onClick = onUndo,
                    enabled = canUndo,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(if (canUndo) PaleBlue else Color(0xFFEEEEEE))
                        .testTag("studio_undo_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Undo,
                        contentDescription = "Undo",
                        tint = if (canUndo) TextPrimaryDark else Color.LightGray
                    )
                }

                IconButton(
                    onClick = onRedo,
                    enabled = canRedo,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(if (canRedo) PaleBlue else Color(0xFFEEEEEE))
                        .testTag("studio_redo_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Redo,
                        contentDescription = "Redo",
                        tint = if (canRedo) TextPrimaryDark else Color.LightGray
                    )
                }

                IconButton(
                    onClick = onClear,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(PalePeach)
                        .testTag("studio_clear_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Clear",
                        tint = CoralRed,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Done & Save Button
                Button(
                    onClick = onDone,
                    colors = ButtonDefaults.buttonColors(containerColor = GrassGreen),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .testTag("studio_done_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Done!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun StudioControlPanel(
    selectedTool: ToolType,
    selectedColor: CrayonColor,
    currentPaletteIndex: Int,
    brushSize: Float,
    onSelectTool: (ToolType) -> Unit,
    onSelectColor: (CrayonColor) -> Unit,
    onSelectPaletteIndex: (Int) -> Unit,
    onSelectBrushSize: (Float) -> Unit
) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        shadowElevation = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            // Row 1: Tools & Brush Sizes
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tool Selectors
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ToolType.entries.forEach { tool ->
                        ToolButton(
                            tool = tool,
                            isSelected = tool == selectedTool,
                            onClick = { onSelectTool(tool) }
                        )
                    }
                }

                // Brush Size Selectors (Small, Medium, Large)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BrushSizeDot(size = 14f, isSelected = brushSize == 14f, onClick = { onSelectBrushSize(14f) })
                    BrushSizeDot(size = 28f, isSelected = brushSize == 28f, onClick = { onSelectBrushSize(28f) })
                    BrushSizeDot(size = 46f, isSelected = brushSize == 46f, onClick = { onSelectBrushSize(46f) })
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Row 2: Palette Tabs (Classic, Pastel, Sparkle)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                val palettes = listOf("🖍️ Classic", "🧁 Pastel", "✨ Glitter")
                palettes.forEachIndexed { index, name ->
                    val isSelected = currentPaletteIndex == index
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isSelected) WarmOrange else Color(0xFFF0F1F3),
                        modifier = Modifier
                            .clickable { onSelectPaletteIndex(index) }
                            .testTag("palette_tab_$index")
                    ) {
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isSelected) Color.White else TextPrimaryDark,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            // Row 3: Color Crayons Swatches
            val activeColorList = when (currentPaletteIndex) {
                1 -> PredefinedPalettes.pastelColors
                2 -> PredefinedPalettes.glitterColors
                else -> PredefinedPalettes.primaryCrayons
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
            ) {
                items(activeColorList) { crayon ->
                    CrayonSwatch(
                        color = crayon,
                        isSelected = crayon.name == selectedColor.name,
                        onClick = { onSelectColor(crayon) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ToolButton(
    tool: ToolType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) PalePeach else Color(0xFFF5F5F7),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) WarmOrange else Color(0x18000000)
        ),
        modifier = Modifier
            .clickable(onClick = onClick)
            .testTag("tool_${tool.name.lowercase()}")
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = tool.emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = tool.title,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = if (isSelected) WarmOrange else TextPrimaryDark
            )
        }
    }
}

@Composable
private fun BrushSizeDot(
    size: Float,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val displaySize = when (size) {
        14f -> 10.dp
        28f -> 18.dp
        else -> 26.dp
    }

    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(if (isSelected) PalePeach else Color(0xFFF2F2F2))
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) WarmOrange else Color.Transparent,
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(displaySize)
                .clip(CircleShape)
                .background(if (isSelected) WarmOrange else Color.DarkGray)
        )
    }
}

@Composable
private fun CrayonSwatch(
    color: CrayonColor,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .testTag("color_${color.name.lowercase().replace(" ", "_")}")
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .shadow(if (isSelected) 4.dp else 2.dp, CircleShape)
                .clip(CircleShape)
                .background(Color(color.colorInt))
                .border(
                    width = if (isSelected) 3.5.dp else 1.5.dp,
                    color = if (isSelected) Color.White else Color(0x33000000),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Text(text = "✓", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }

        Text(
            text = color.name,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Normal
            ),
            color = TextPrimaryDark,
            maxLines = 1,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
private fun CelebrationOverlay(
    artwork: SavedArtwork?,
    stars: Int,
    completionPercent: Int,
    isLowCompletion: Boolean,
    onShare: () -> Unit,
    onViewGallery: () -> Unit,
    onColorAnother: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .testTag("celebration_overlay"),
        contentAlignment = Alignment.Center
    ) {
        // Confetti burst on successful star award
        if (!isLowCompletion) {
            ConfettiCelebration()
        }

        Surface(
            shape = RoundedCornerShape(32.dp),
            color = Color.White,
            shadowElevation = 12.dp,
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (isLowCompletion) "🎨" else "🎉", fontSize = 50.sp)

                Text(
                    text = if (isLowCompletion) "Good Start, Super Artist!" else "Awesome Job, Super Artist!",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp
                    ),
                    color = TextPrimaryDark,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Real completion percentage
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = PaleMint,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GrassGreen.copy(alpha = 0.4f)),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "🎨 You colored $completionPercent%!",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = GrassGreen,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }

                if (isLowCompletion) {
                    // Less than 20% prompt
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = PalePeach,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = "Keep coloring! Color more of the picture to earn stars 🌈",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFFD35400),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                } else {
                    // Star awards
                    val starIcons = "⭐".repeat(stars.coerceAtLeast(1))
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = SunshineYellow.copy(alpha = 0.35f),
                        border = androidx.compose.foundation.BorderStroke(2.dp, SunshineYellow),
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$starIcons $stars ${if (stars == 1) "Star" else "Stars"} Earned!",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp,
                                color = TextPrimaryDark
                            )
                        }
                    }
                }

                Text(
                    text = "Your masterpiece has been safely saved in your artwork gallery!",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryDark,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KidButton(
                        text = "Share Artwork 📤",
                        onClick = onShare,
                        backgroundColor = SkyBlue,
                        modifier = Modifier.fillMaxWidth().testTag("celebration_share_button")
                    )

                    KidButton(
                        text = "My Gallery 🖼️",
                        onClick = onViewGallery,
                        backgroundColor = WarmOrange,
                        modifier = Modifier.fillMaxWidth().testTag("celebration_gallery_button")
                    )

                    OutlinedButton(
                        onClick = onColorAnother,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("celebration_color_another_button")
                    ) {
                        Text(
                            text = "Color Another Page 🎨",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}
