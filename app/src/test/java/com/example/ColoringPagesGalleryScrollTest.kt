package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.domain.coloring.ColoringPageTemplates
import com.example.ui.categories.CategoryScreen
import com.example.ui.theme.MyApplicationTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ColoringPagesGalleryScrollTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAllColoringPagesCollectionContainsPages() {
        val allPages = ColoringPageTemplates.allPreloadedPages
        assertTrue("Collection should contain at least 110 pages", allPages.size >= 110)

        // Verify all 11 categories each contribute at least 10 pages
        val nonAllCategories = Category.entries.filter { it != Category.ALL }
        assertEquals(11, nonAllCategories.size)
        for (cat in nonAllCategories) {
            val pages = ColoringPageTemplates.getPagesForCategory(cat)
            assertTrue("Category ${cat.title} should have at least 10 pages", pages.size >= 10)
        }

        // Verify Category.ALL returns all pages
        val allFromCategory = ColoringPageTemplates.getPagesForCategory(Category.ALL)
        assertEquals(allPages.size, allFromCategory.size)
    }

    @Test
    fun testCategoryScreenRendersAndSupportsVerticalScrollThroughCollection() {
        val allPages = ColoringPageTemplates.allPreloadedPages
        var selectedPage: ColoringPage? = null
        var backClicked = false

        composeTestRule.setContent {
            MyApplicationTheme {
                CategoryScreen(
                    category = Category.ALL,
                    pages = allPages,
                    onSelectPage = { page -> selectedPage = page },
                    onBackClick = { backClicked = true }
                )
            }
        }

        // 1. Verify header displays Coloring Pages count
        composeTestRule.onNodeWithText("🎨 All Drawings").assertIsDisplayed()
        composeTestRule.onNodeWithText("${allPages.size} Coloring Pages 🎨").assertIsDisplayed()

        // 2. Verify grid is present
        val gridNode = composeTestRule.onNodeWithTag("category_pages_grid")
        gridNode.assertIsDisplayed()

        // 3. Verify top item (Friendly T-Rex) is visible
        composeTestRule.onNodeWithTag("page_card_t_rex").assertIsDisplayed()

        // 4. Test clicking top item invokes onSelectPage
        composeTestRule.onNodeWithTag("page_card_t_rex").performClick()
        assertNotNull(selectedPage)
        assertEquals("t_rex", selectedPage?.id)

        // 5. Test vertical touch scrolling
        gridNode.performTouchInput {
            swipeUp()
        }

        // 6. Test that scrolled down items are reachable all the way to items in the collection
        gridNode.performScrollToNode(hasTestTag("page_card_igloo"))
        composeTestRule.onNodeWithTag("page_card_igloo").assertIsDisplayed()

        // Test clicking item invokes onSelectPage with igloo
        composeTestRule.onNodeWithTag("page_card_igloo").performClick()
        assertNotNull(selectedPage)
        assertEquals("igloo", selectedPage?.id)

        // 7. Verify back button works
        composeTestRule.onNodeWithTag("category_back_button").performClick()
        assertTrue(backClicked)
    }

    @Test
    fun testSpecificCategoryScreenScrollable() {
        val dinoPages = ColoringPageTemplates.getPagesForCategory(Category.DINOSAURS)
        assertTrue(dinoPages.size >= 10)

        var selectedPage: ColoringPage? = null

        composeTestRule.setContent {
            MyApplicationTheme {
                CategoryScreen(
                    category = Category.DINOSAURS,
                    pages = dinoPages,
                    onSelectPage = { selectedPage = it },
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("🦖 Dinosaurs").assertIsDisplayed()
        composeTestRule.onNodeWithText("${dinoPages.size} Coloring Pages 🎨").assertIsDisplayed()
        composeTestRule.onNodeWithTag("category_pages_grid").assertIsDisplayed()

        // First dinosaur
        composeTestRule.onNodeWithTag("page_card_t_rex").assertIsDisplayed()
        // Dinosaur item reachable via scroll
        composeTestRule.onNodeWithTag("category_pages_grid").performTouchInput {
            swipeUp()
        }
        composeTestRule.onNodeWithTag("category_pages_grid")
            .performScrollToNode(hasTestTag("page_card_dino_volcano"))
        composeTestRule.onNodeWithTag("page_card_dino_volcano")
            .assertIsDisplayed()
    }
}
