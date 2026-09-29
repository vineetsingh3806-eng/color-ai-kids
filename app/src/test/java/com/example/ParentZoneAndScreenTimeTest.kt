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
import com.example.ui.components.ParentalGateSecurity
import com.example.ui.components.ScreenTimeBreakDialog
import com.example.ui.parent.ParentZoneScreen
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
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

    @Before
    fun setUp() {
        ParentalGateSecurity.resetForTesting()
    }

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

        // Verify family safety badge card exists and does NOT contain "100% Ad-Free"
        composeTestRule.onNodeWithText("Designed for Families & Child Safety").assertIsDisplayed()
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
        assertEquals(1, ParentalGateSecurity.failedAttempts)
    }

    @Test
    fun testParentalGateCorrectAnswerGrantsAccess() {
        var success = false

        composeTestRule.setContent {
            MyApplicationTheme {
                ParentalGateDialog(
                    onDismiss = {},
                    onSuccess = { success = true },
                    initialNum1 = 7,
                    initialNum2 = 8
                )
            }
        }

        // 7 * 8 = 56
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("56")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()

        assertTrue("Correct answer should trigger success", success)
        assertEquals("Failed attempts must be reset on success", 0, ParentalGateSecurity.failedAttempts)
        assertFalse(ParentalGateSecurity.isCoolingDown())
    }

    @Test
    fun testParentalGateThreeFailedAttemptsActivatesCooldown() {
        var testTime = 100_000L
        ParentalGateSecurity.clock = { testTime }

        var success = false

        composeTestRule.setContent {
            MyApplicationTheme {
                ParentalGateDialog(
                    onDismiss = {},
                    onSuccess = { success = true },
                    initialNum1 = 7,
                    initialNum2 = 8
                )
            }
        }

        // Attempt 1: wrong
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("9991")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()
        assertFalse(success)
        assertEquals(1, ParentalGateSecurity.failedAttempts)
        assertFalse(ParentalGateSecurity.isCoolingDown())

        // Attempt 2: wrong
        composeTestRule.onNodeWithTag("parental_gate_input").performTextClearance()
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("9992")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()
        assertFalse(success)
        assertEquals(2, ParentalGateSecurity.failedAttempts)
        assertFalse(ParentalGateSecurity.isCoolingDown())

        // Attempt 3: wrong -> triggers 30s cooldown
        composeTestRule.onNodeWithTag("parental_gate_input").performTextClearance()
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("9993")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()
        assertFalse(success)
        assertEquals(3, ParentalGateSecurity.failedAttempts)
        assertTrue(ParentalGateSecurity.isCoolingDown())

        // Verify cooldown UI
        composeTestRule.onNodeWithTag("parental_gate_cooldown_message").assertIsDisplayed()
        composeTestRule.onNodeWithTag("parental_gate_submit").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("parental_gate_input").assertIsNotEnabled()
    }

    @Test
    fun testClosingAndReopeningDialogDoesNotBypassCooldown() {
        var testTime = 200_000L
        ParentalGateSecurity.clock = { testTime }

        // Trigger 3 failed attempts
        ParentalGateSecurity.recordFailedAttempt()
        ParentalGateSecurity.recordFailedAttempt()
        ParentalGateSecurity.recordFailedAttempt()
        assertTrue("Cooldown should be active after 3 failed attempts", ParentalGateSecurity.isCoolingDown())

        // Simulate opening dialog after being dismissed
        composeTestRule.setContent {
            MyApplicationTheme {
                ParentalGateDialog(
                    onDismiss = {},
                    onSuccess = {}
                )
            }
        }

        // Verify dialog opens directly into cooldown state
        composeTestRule.onNodeWithTag("parental_gate_cooldown_message").assertIsDisplayed()
        composeTestRule.onNodeWithTag("parental_gate_submit").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("parental_gate_input").assertIsNotEnabled()
    }

    @Test
    fun testAccessBecomesAvailableAfterCooldownExpires() {
        var testTime = 300_000L
        ParentalGateSecurity.clock = { testTime }

        // Trigger 3 failed attempts
        ParentalGateSecurity.recordFailedAttempt()
        ParentalGateSecurity.recordFailedAttempt()
        ParentalGateSecurity.recordFailedAttempt()
        assertTrue(ParentalGateSecurity.isCoolingDown())

        // Advance monotonic time past the 30s cooldown (31s)
        testTime += 31_000L
        assertFalse("Cooldown should have expired after 31 seconds", ParentalGateSecurity.isCoolingDown())

        var success = false

        composeTestRule.setContent {
            MyApplicationTheme {
                ParentalGateDialog(
                    onDismiss = {},
                    onSuccess = { success = true },
                    initialNum1 = 6,
                    initialNum2 = 9
                )
            }
        }

        // Verify UI is enabled again
        composeTestRule.onNodeWithTag("parental_gate_cooldown_message").assertDoesNotExist()
        composeTestRule.onNodeWithTag("parental_gate_submit").assertIsEnabled()
        composeTestRule.onNodeWithTag("parental_gate_input").assertIsEnabled()

        // 6 * 9 = 54
        composeTestRule.onNodeWithTag("parental_gate_input").performTextInput("54")
        composeTestRule.onNodeWithTag("parental_gate_submit").performClick()
        assertTrue("Access granted after cooldown expiry with correct answer", success)
    }

    @Test
    fun testScreenTimeBreakDialogOpensParentalGateAndSharesSecurity() {
        var extraTimeAdded = 0

        composeTestRule.setContent {
            MyApplicationTheme {
                ScreenTimeBreakDialog(
                    limitMinutes = 15,
                    onTakeBreak = {},
                    onAddExtraTime = { extraTimeAdded = it },
                    onDismissToday = {},
                    onOpenParentZone = {}
                )
            }
        }

        // Click unlock button in Screen Time Break dialog
        composeTestRule.onNodeWithTag("screen_time_parent_unlock_button").performClick()

        // Verify Parental Gate dialog is opened
        composeTestRule.onNodeWithTag("parental_gate_dialog").assertIsDisplayed()
        composeTestRule.onNodeWithText("Grown-Ups Only").assertIsDisplayed()
        composeTestRule.onNodeWithTag("parental_gate_input").assertIsDisplayed()
    }
}
