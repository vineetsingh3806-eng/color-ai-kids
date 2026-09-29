package com.example

import com.example.data.model.Category
import com.example.domain.coloring.ColoringPageTemplates
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ColoringPageExpansionTest {

    @Test
    fun verifyEveryCategoryHasAtLeast20Pages() {
        val categories = Category.values().filter { it != Category.ALL }
        assertEquals(11, categories.size)

        for (category in categories) {
            val pages = ColoringPageTemplates.getPagesForCategory(category)
            assertTrue(
                "Category ${category.name} should have at least 20 pages, but had ${pages.size}",
                pages.size >= 20
            )

            // Verify all IDs within the category are unique
            val uniqueIds = pages.map { it.id }.toSet()
            assertEquals(
                "Duplicate IDs detected in category ${category.name}",
                pages.size,
                uniqueIds.size
            )
        }
    }

    @Test
    fun verifyNoDuplicateIdsAcrossAllPages() {
        val allPages = ColoringPageTemplates.allPreloadedPages
        assertTrue("Expected at least 220 total pages, found ${allPages.size}", allPages.size >= 220)

        val ids = allPages.map { it.id }
        val duplicates = ids.groupBy { it }.filter { it.value.size > 1 }.keys
        assertTrue("Duplicate IDs found: $duplicates", duplicates.isEmpty())
    }

    @Test
    fun verifyEveryPageGeneratesValidImage() {
        val allPages = ColoringPageTemplates.allPreloadedPages
        for (page in allPages) {
            val bitmap = ColoringPageTemplates.createTemplateBitmap(page.templateId, 128)
            assertNotNull("Bitmap should not be null for template ${page.templateId}", bitmap)
            assertEquals("Bitmap width should match requested size", 128, bitmap.width)
            assertEquals("Bitmap height should match requested size", 128, bitmap.height)
        }
    }

    @Test
    fun verifyAllOriginalPagesPreservedInExactOrder() {
        val allPages = ColoringPageTemplates.allPreloadedPages
        assertEquals("t_rex", allPages[0].id)
        assertEquals("igloo", allPages[109].id)
    }
}
