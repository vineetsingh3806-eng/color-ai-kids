package com.example.domain.coloring

import android.graphics.Bitmap
import android.graphics.Color
import java.util.ArrayDeque

object FloodFill {

    /**
     * Efficient queue-based flood fill on an Android Bitmap.
     * Respects line art outlines (dark boundary pixels) and fills enclosed regions smoothly.
     */
    fun fill(
        bitmap: Bitmap,
        startX: Int,
        startY: Int,
        fillColorInt: Int,
        tolerance: Int = 36
    ): Boolean {
        val width = bitmap.width
        val height = bitmap.height

        if (startX !in 0 until width || startY !in 0 until height) {
            return false
        }

        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val targetColor = pixels[startY * width + startX]

        // If target color is already the fill color, no-op
        if (targetColor == fillColorInt) {
            return false
        }

        // Do not fill if the user tapped directly on a dark outline
        if (isOutlineColor(targetColor)) {
            return false
        }

        val visited = BooleanArray(width * height)
        val queue = ArrayDeque<Int>() // packed x, y: (y shl 16) or x

        val startPacked = (startY shl 16) or (startX and 0xFFFF)
        queue.add(startPacked)
        visited[startY * width + startX] = true

        val targetR = Color.red(targetColor)
        val targetG = Color.green(targetColor)
        val targetB = Color.blue(targetColor)

        while (queue.isNotEmpty()) {
            val packed = queue.poll() ?: break
            val y = packed ushr 16
            val x = packed and 0xFFFF
            val index = y * width + x

            pixels[index] = fillColorInt

            // Check 4-connected neighbors: North, South, West, East
            if (x > 0) checkNeighbor(x - 1, y, width, height, pixels, visited, queue, targetR, targetG, targetB, tolerance)
            if (x < width - 1) checkNeighbor(x + 1, y, width, height, pixels, visited, queue, targetR, targetG, targetB, tolerance)
            if (y > 0) checkNeighbor(x, y - 1, width, height, pixels, visited, queue, targetR, targetG, targetB, tolerance)
            if (y < height - 1) checkNeighbor(x, y + 1, width, height, pixels, visited, queue, targetR, targetG, targetB, tolerance)
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return true
    }

    private fun checkNeighbor(
        nx: Int,
        ny: Int,
        width: Int,
        height: Int,
        pixels: IntArray,
        visited: BooleanArray,
        queue: ArrayDeque<Int>,
        tr: Int,
        tg: Int,
        tb: Int,
        tolerance: Int
    ) {
        val nIndex = ny * width + nx
        if (visited[nIndex]) return

        val nColor = pixels[nIndex]
        if (isOutlineColor(nColor)) {
            // It's an outline, stop expansion
            visited[nIndex] = true
            return
        }

        val nr = Color.red(nColor)
        val ng = Color.green(nColor)
        val nb = Color.blue(nColor)

        val diff = Math.abs(nr - tr) + Math.abs(ng - tg) + Math.abs(nb - tb)
        if (diff <= tolerance * 3) {
            visited[nIndex] = true
            queue.add((ny shl 16) or (nx and 0xFFFF))
        }
    }

    /**
     * Determines if a pixel belongs to a line art outline.
     * Line art outlines in coloring pages are dark (near black).
     */
    private fun isOutlineColor(color: Int): Boolean {
        val a = Color.alpha(color)
        if (a < 50) return false // Transparent isn't an outline
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        // High darkness check: outlines are typically dark black/gray (lum < 75)
        return (r < 75 && g < 75 && b < 75)
    }
}
