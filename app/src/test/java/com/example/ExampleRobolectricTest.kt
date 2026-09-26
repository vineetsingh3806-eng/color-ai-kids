package com.example

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.test.core.app.ApplicationProvider
import com.example.domain.coloring.ColoringPageTemplates
import com.example.domain.coloring.FloodFill
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun testAppName() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("ColorAI Kids", appName)
    }

    @Test
    fun testFloodFill() {
        // Create 20x20 white bitmap with a black border dividing it
        val bmp = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        val pixels = IntArray(20 * 20) { Color.WHITE }
        // Draw vertical black line at x = 10
        for (y in 0 until 20) {
            pixels[y * 20 + 10] = Color.BLACK
        }
        bmp.setPixels(pixels, 0, 20, 0, 0, 20, 20)

        // Fill left side at (5, 5) with RED
        val filled = FloodFill.fill(bmp, 5, 5, Color.RED)
        assertTrue(filled)

        // Verify left side changed to RED
        assertEquals(Color.RED, bmp.getPixel(5, 5))
        // Verify right side remained WHITE (did not cross black border)
        assertEquals(Color.WHITE, bmp.getPixel(15, 5))
        // Verify black line remained BLACK
        assertEquals(Color.BLACK, bmp.getPixel(10, 5))
    }

    @Test
    fun testColoringPageTemplates() {
        val bitmap = ColoringPageTemplates.createTemplateBitmap("t_rex")
        assertEquals(1024, bitmap.width)
        assertEquals(1024, bitmap.height)
    }

    @Test
    fun testValidateAllColoringTemplates() {
        val valid = ColoringPageTemplates.validateAllColoringTemplates()
        assertTrue("All coloring templates should validate", valid)
        assertTrue("Should have at least 110 coloring pages", ColoringPageTemplates.allPreloadedPages.size >= 110)
    }

    @Test
    fun testColoringProgressCalculatorThresholds() {
        assertEquals(0, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(0))
        assertEquals(0, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(19))
        assertEquals(1, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(20))
        assertEquals(1, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(39))
        assertEquals(2, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(40))
        assertEquals(2, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(59))
        assertEquals(3, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(60))
        assertEquals(3, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(74))
        assertEquals(4, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(75))
        assertEquals(4, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(89))
        assertEquals(5, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(90))
        assertEquals(5, com.example.domain.coloring.ColoringProgressCalculator.calculateStars(100))
    }

    @Test
    fun testColoringProgressCalculatorUncoloredTemplateIsZero() {
        val templateBmp = ColoringPageTemplates.createTemplateBitmap("t_rex")
        val canvasBmp = templateBmp.copy(Bitmap.Config.ARGB_8888, true)
        val percent = com.example.domain.coloring.ColoringProgressCalculator.calculateColoringCompletionPercent(
            templateBmp,
            canvasBmp
        )
        assertEquals(0, percent)
    }

    @Test
    fun testColoringProgressCalculatorColoredAreaIncreases() {
        val templateBmp = ColoringPageTemplates.createTemplateBitmap("puppy")
        val canvasBmp = templateBmp.copy(Bitmap.Config.ARGB_8888, true)

        // Set pixels in the center of the canvas with red
        for (y in 400..600 step 2) {
            for (x in 400..600 step 2) {
                canvasBmp.setPixel(x, y, Color.RED)
            }
        }

        val percent = com.example.domain.coloring.ColoringProgressCalculator.calculateColoringCompletionPercent(
            templateBmp,
            canvasBmp
        )
        assertTrue("Colored percentage should be greater than zero, was: $percent", percent > 0)
        val stars = com.example.domain.coloring.ColoringProgressCalculator.calculateStars(percent)
        assertTrue("Stars should be between 0 and 5", stars in 0..5)
    }
}
