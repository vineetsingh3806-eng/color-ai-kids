package com.example.domain.coloring

import android.graphics.Bitmap
import android.graphics.Color
import java.util.ArrayDeque

/**
 * High-performance calculator for coloring completion percentage and star rewards.
 * Distinguishes colorable artwork regions from background, outlines, details, and anti-aliased edges.
 */
object ColoringProgressCalculator {

    class ColorableMask(
        val gridWidth: Int,
        val gridHeight: Int,
        val step: Int,
        val colorablePoints: IntArray // Packed as (x shl 16) or y
    ) {
        val totalCount: Int get() = colorablePoints.size
    }

    /**
     * Determines stars to award based on exact user thresholds:
     * 0–19% = 0 stars
     * 20–39% = 1 star
     * 40–59% = 2 stars
     * 60–74% = 3 stars
     * 75–89% = 4 stars
     * 90–100% = 5 stars
     */
    fun calculateStars(percent: Int): Int = when {
        percent < 20 -> 0
        percent in 20..39 -> 1
        percent in 40..59 -> 2
        percent in 60..74 -> 3
        percent in 75..89 -> 4
        else -> 5 // 90..100
    }

    /**
     * Builds a fast colorable mask from base outline bitmap.
     * Samples on a 256x256 grid (step = 4 for a 1024x1024 bitmap)
     * so evaluations execute in ~1-2 milliseconds.
     */
    fun buildColorableMask(baseBitmap: Bitmap, step: Int = 4): ColorableMask {
        val bmpWidth = baseBitmap.width
        val bmpHeight = baseBitmap.height
        val gridW = bmpWidth / step
        val gridH = bmpHeight / step

        val isOutline = BooleanArray(gridW * gridH)
        var minX = gridW
        var maxX = 0
        var minY = gridH
        var maxY = 0

        // 1. Identify outline and detail pixels on the grid
        for (gy in 0 until gridH) {
            val by = (gy * step).coerceAtMost(bmpHeight - 1)
            for (gx in 0 until gridW) {
                val bx = (gx * step).coerceAtMost(bmpWidth - 1)
                val pixel = baseBitmap.getPixel(bx, by)
                val alpha = Color.alpha(pixel)
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                val lum = (0.299 * r + 0.587 * g + 0.114 * b).toInt()

                // If pixel is opaque and dark outline edge (alpha > 128 && lum < 200), mark as outline
                if (alpha > 128 && lum < 200) {
                    isOutline[gy * gridW + gx] = true
                    if (gx < minX) minX = gx
                    if (gx > maxX) maxX = gx
                    if (gy < minY) minY = gy
                    if (gy > maxY) maxY = gy
                }
            }
        }

        // 2. Flood-fill from outer canvas borders to identify outer white page background
        val isExteriorBg = BooleanArray(gridW * gridH)
        val queue = ArrayDeque<Int>()

        fun enqueueBorder(gx: Int, gy: Int) {
            val idx = gy * gridW + gx
            if (!isOutline[idx] && !isExteriorBg[idx]) {
                isExteriorBg[idx] = true
                queue.add(idx)
            }
        }

        // Add top and bottom borders
        for (gx in 0 until gridW) {
            enqueueBorder(gx, 0)
            enqueueBorder(gx, gridH - 1)
        }
        // Add left and right borders
        for (gy in 0 until gridH) {
            enqueueBorder(0, gy)
            enqueueBorder(gridW - 1, gy)
        }

        // BFS through non-outline pixels to flood exterior background
        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            val cx = curr % gridW
            val cy = curr / gridW

            val neighbors = intArrayOf(
                cy * gridW + (cx - 1),
                cy * gridW + (cx + 1),
                (cy - 1) * gridW + cx,
                (cy + 1) * gridW + cx
            )

            if (cx > 0 && !isOutline[neighbors[0]] && !isExteriorBg[neighbors[0]]) {
                isExteriorBg[neighbors[0]] = true
                queue.add(neighbors[0])
            }
            if (cx < gridW - 1 && !isOutline[neighbors[1]] && !isExteriorBg[neighbors[1]]) {
                isExteriorBg[neighbors[1]] = true
                queue.add(neighbors[1])
            }
            if (cy > 0 && !isOutline[neighbors[2]] && !isExteriorBg[neighbors[2]]) {
                isExteriorBg[neighbors[2]] = true
                queue.add(neighbors[2])
            }
            if (cy < gridH - 1 && !isOutline[neighbors[3]] && !isExteriorBg[neighbors[3]]) {
                isExteriorBg[neighbors[3]] = true
                queue.add(neighbors[3])
            }
        }

        // 3. Enclosed regions are non-outline, non-exterior-background grid cells
        val enclosedList = IntArray(gridW * gridH)
        var enclosedCount = 0

        for (gy in 0 until gridH) {
            for (gx in 0 until gridW) {
                val idx = gy * gridW + gx
                if (!isOutline[idx] && !isExteriorBg[idx]) {
                    enclosedList[enclosedCount++] = (gx shl 16) or gy
                }
            }
        }

        // Fallback for open line-art or when few lines enclose completely
        val minEnclosedThreshold = (gridW * gridH * 0.03).toInt() // At least 3% of canvas
        val finalPoints = if (enclosedCount >= minEnclosedThreshold) {
            enclosedList.copyOf(enclosedCount)
        } else {
            // Include interior canvas within margin (excluding outer margin and outlines)
            var fallbackCount = 0
            val fallbackList = IntArray(gridW * gridH)
            val marginX = (gridW * 0.05).toInt()
            val marginY = (gridH * 0.05).toInt()

            val startX = if (minX < maxX) (minX + 2).coerceIn(0, gridW - 1) else marginX
            val endX = if (minX < maxX) (maxX - 2).coerceIn(0, gridW - 1) else gridW - 1 - marginX
            val startY = if (minY < maxY) (minY + 2).coerceIn(0, gridH - 1) else marginY
            val endY = if (minY < maxY) (maxY - 2).coerceIn(0, gridH - 1) else gridH - 1 - marginY

            for (gy in startY..endY) {
                for (gx in startX..endX) {
                    val idx = gy * gridW + gx
                    if (!isOutline[idx]) {
                        fallbackList[fallbackCount++] = (gx shl 16) or gy
                    }
                }
            }
            if (fallbackCount > 0) {
                fallbackList.copyOf(fallbackCount)
            } else {
                enclosedList.copyOf(enclosedCount.coerceAtLeast(1))
            }
        }

        return ColorableMask(gridW, gridH, step, finalPoints)
    }

    /**
     * Checks if a pixel on the canvas is colored (changed from white canvas).
     * White, near-white, or transparent pixels count as uncolored.
     */
    fun isPixelColored(pixel: Int): Boolean {
        val alpha = Color.alpha(pixel)
        if (alpha < 50) return false // Transparent

        val r = Color.red(pixel)
        val g = Color.green(pixel)
        val b = Color.blue(pixel)

        // Near-white is uncolored canvas (e.g. after clear or eraser)
        val maxC = maxOf(r, g, b)
        val minC = minOf(r, g, b)
        val diff = maxC - minC
        val lum = (0.299 * r + 0.587 * g + 0.114 * b).toInt()

        // Pure white or faint off-white background
        if (lum >= 242 && diff <= 18) {
            return false
        }

        return true
    }

    /**
     * Evaluates the current colored percentage (0..100) using a precomputed mask.
     * Takes ~1ms, ideal for live progress updates.
     */
    fun evaluateProgress(mask: ColorableMask, currentCanvas: Bitmap): Int {
        val total = mask.totalCount
        if (total == 0) return 0

        val bmpW = currentCanvas.width
        val bmpH = currentCanvas.height
        val step = mask.step
        var coloredCount = 0

        for (i in 0 until total) {
            val packed = mask.colorablePoints[i]
            val gx = packed ushr 16
            val gy = packed and 0xFFFF

            val bx = (gx * step).coerceAtMost(bmpW - 1)
            val by = (gy * step).coerceAtMost(bmpH - 1)
            val pixel = currentCanvas.getPixel(bx, by)

            if (isPixelColored(pixel)) {
                coloredCount++
            }
        }

        val percent = (coloredCount * 100.0 / total).toInt()
        return percent.coerceIn(0, 100)
    }

    /**
     * Full completion calculation comparing base outline bitmap and current canvas.
     */
    fun calculateColoringCompletionPercent(
        baseOutlineBitmap: Bitmap,
        canvasBitmap: Bitmap
    ): Int {
        val mask = buildColorableMask(baseOutlineBitmap, step = 4)
        return evaluateProgress(mask, canvasBitmap)
    }
}
