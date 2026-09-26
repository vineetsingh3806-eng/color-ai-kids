package com.example

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.ArtworkEntity
import com.example.data.local.RecentPageEntity
import com.example.data.local.UserStatsEntity
import com.example.data.model.ParentSettings
import com.example.data.repository.ArtworkRepository
import com.example.data.repository.ColoringRepository
import com.example.data.repository.RewardsRepository
import com.example.domain.screentime.ScreenTimeManager
import com.example.ui.components.ParentalGateDialog
import com.example.ui.parent.ParentZoneScreen
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ParentZoneAndScreenTimeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testParentZoneRendersWithoutAnyAiControls() {
        var clearAllDataCalled = false
        var updatedSettings: ParentSettings? = null

        composeTestRule.setContent {
            MyApplicationTheme {
                ParentZoneScreen(
                    parentSettings = ParentSettings(screenTimeLimitMinutes = 15),
                    usageSecondsToday = 300L,
                    onUpdateSettings = { updatedSettings = it },
                    onClearAllData = { clearAllDataCalled = true },
                    onBackClick = {}
                )
            }
        }

        // Verify title
        composeTestRule.onNodeWithText("🔒 Parent Zone").assertIsDisplayed()

        // Verify NO AI Generation controls exist
        composeTestRule.onNodeWithText("✨ AI Generation Controls").assertDoesNotExist()
        composeTestRule.onNodeWithText("Enable AI Creation").assertDoesNotExist()
        composeTestRule.onNodeWithText("Allow generating new coloring pages").assertDoesNotExist()

        // Verify COPPA / Google Play Families card exists and does NOT contain "100% Ad-Free"
        composeTestRule.onNodeWithText("Google Play Families & COPPA Compliant").assertIsDisplayed()
        composeTestRule.onNodeWithText("Zero Data Tracking • Safe Local Storage").assertIsDisplayed()
        composeTestRule.onNodeWithText("100% Ad-Free • Zero Data Tracking • Safe Local Storage").assertDoesNotExist()

        // Verify Screen Time card & options
        composeTestRule.onNodeWithText("⏱️ Screen Time Play Limit").assertIsDisplayed()
        composeTestRule.onNodeWithTag("screen_time_limit_0").assertIsDisplayed()
        composeTestRule.onNodeWithTag("screen_time_limit_15").assertIsDisplayed()
        composeTestRule.onNodeWithTag("screen_time_limit_30").assertIsDisplayed()
        composeTestRule.onNodeWithTag("screen_time_limit_45").assertIsDisplayed()
        composeTestRule.onNodeWithTag("screen_time_limit_60").assertIsDisplayed()

        // Test changing screen time limit
        composeTestRule.onNodeWithTag("screen_time_limit_30").performClick()
        assertNotNull(updatedSettings)
        assertEquals(30, updatedSettings?.screenTimeLimitMinutes)

        // Verify Privacy Policy card and check its dialog text
        composeTestRule.onNodeWithTag("parent_privacy_policy_card").performScrollTo().performClick()
        composeTestRule.onNodeWithText("🛡️ Children's Privacy Policy").assertIsDisplayed()
        composeTestRule.onNodeWithText("100% Ad-Free", substring = true).assertDoesNotExist()
        composeTestRule.onNodeWithText("Safe AI", substring = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("privacy_policy_confirm_button").performClick()

        // Verify Clear All Data confirmation flow
        composeTestRule.onNodeWithTag("parent_clear_all_button").performScrollTo().performClick()
        composeTestRule.onNodeWithText("Are you sure?").assertIsDisplayed()
        composeTestRule.onNodeWithTag("confirm_delete_everything_button").performClick()
        assertTrue(clearAllDataCalled)
    }

    @Test
    fun testScreenTimeManagerTrackingAndPersistence() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val scope = CoroutineScope(Dispatchers.Unconfined)
        val manager = ScreenTimeManager(context, scope)

        // Reset any existing prefs
        manager.resetUsageData()
        manager.updateSettings(ParentSettings(screenTimeLimitMinutes = 0))

        // 1. When limit is 0 (Off), isLimitReached is always false
        manager.simulateUsageSeconds(1000L)
        assertFalse("Limit of 0 (Off) should never trigger isLimitReached", manager.isLimitReached.value)

        // 2. Set limit to 15 minutes (900 seconds)
        manager.updateSettings(ParentSettings(screenTimeLimitMinutes = 15))

        // Usage at 500s should NOT trigger limit
        manager.simulateUsageSeconds(500L)
        assertFalse(manager.isLimitReached.value)

        // Usage at 900s (15 min) SHOULD trigger limit
        manager.simulateUsageSeconds(900L)
        assertTrue(manager.isLimitReached.value)

        // 3. Test dismiss for today
        manager.dismissReminderForToday()
        assertFalse("After dismiss, isLimitReached should be false", manager.isLimitReached.value)

        // 4. Test extra time (+15 mins)
        manager.addExtraTimeMinutes(15)
        assertEquals(30, manager.settings.value.screenTimeLimitMinutes)
        assertFalse("After adding extra time, 900s is under 30min (1800s)", manager.isLimitReached.value)

        // 5. Test persistence across re-instantiation (app restart)
        val managerAfterRestart = ScreenTimeManager(context, scope)
        assertEquals(30, managerAfterRestart.settings.value.screenTimeLimitMinutes)
        assertEquals(900L, managerAfterRestart.usageSecondsToday.value)

        // 6. Test resetUsageData
        managerAfterRestart.resetUsageData()
        assertEquals(0L, managerAfterRestart.usageSecondsToday.value)
        assertFalse(managerAfterRestart.isLimitReached.value)
    }

    @Test
    fun testClearAllDataFlow() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val database = AppDatabase.getDatabase(context)
        val artworkDao = database.artworkDao()
        val recentPageDao = database.recentPageDao()
        val rewardDao = database.rewardDao()

        val artworkRepo = ArtworkRepository(context, artworkDao)
        val rewardsRepo = RewardsRepository(rewardDao)
        val coloringRepo = ColoringRepository(recentPageDao)
        val screenTimeManager = ScreenTimeManager(context, CoroutineScope(Dispatchers.Unconfined))

        // Seed some sample data
        artworkDao.insertArtwork(
            ArtworkEntity(
                id = "art_1",
                title = "Test Dino",
                categoryId = "dinosaurs",
                imageFilePath = "/tmp/test.png",
                templateId = "t_rex",
                createdAt = System.currentTimeMillis(),
                isCompleted = true,
                starsAwarded = 3
            )
        )
        recentPageDao.recordRecentPage(
            RecentPageEntity(
                pageId = "t_rex",
                title = "T-Rex",
                categoryId = "dinosaurs",
                emoji = "🦖"
            )
        )
        rewardsRepo.addStars(50)
        screenTimeManager.simulateUsageSeconds(600L)

        // Verify seeded data exists
        assertNotNull(artworkDao.getArtworkById("art_1"))
        val statsBefore = rewardDao.getUserStats()
        assertTrue((statsBefore?.totalStars ?: 0) >= 50)

        // Perform Clear All Data flow
        artworkRepo.deleteAllArtworks()
        rewardsRepo.resetAllStats()
        coloringRepo.clearRecentPages()
        screenTimeManager.resetUsageData()

        // Verify all artworks are cleared
        assertNull(artworkDao.getArtworkById("art_1"))

        // Verify stats are reset to default (20 stars, 0 artworks)
        val statsAfter = rewardDao.getUserStats()
        assertEquals(20, statsAfter?.totalStars)
        assertEquals(0, statsAfter?.totalArtworksSaved)

        // Verify screen time usage is reset
        assertEquals(0L, screenTimeManager.usageSecondsToday.value)
    }

    @Test
    fun testParentalGateDialogBehavior() {
        var dismissed = false
        var success = false

        composeTestRule.setContent {
            MyApplicationTheme {
                ParentalGateDialog(
                    onDismiss = { dismissed = true },
                    onSuccess = { success = true }
                )
            }
        }

        // Verify dialog elements
        composeTestRule.onNodeWithTag("parental_gate_dialog").assertIsDisplayed()
        composeTestRule.onNodeWithText("Grown-Ups Only").assertIsDisplayed()
        composeTestRule.onNodeWithTag("parental_gate_input").assertIsDisplayed()

        // Submit wrong answer
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("999999")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()
        assertFalse("Wrong answer should not trigger success", success)
        composeTestRule.onNodeWithText("Incorrect answer, please try again").assertIsDisplayed()
    }
}
