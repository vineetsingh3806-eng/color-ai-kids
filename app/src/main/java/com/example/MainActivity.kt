package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.local.AppDatabase
import com.example.data.local.UserStatsEntity
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.data.model.ParentSettings
import com.example.data.repository.ArtworkRepository
import com.example.data.repository.ColoringRepository
import com.example.data.repository.RewardsRepository
import com.example.domain.coloring.ThumbnailCache
import com.example.domain.screentime.ScreenTimeManager
import com.example.ui.artwork.MyArtworkScreen
import com.example.ui.categories.CategoryScreen
import com.example.ui.coloring.ColoringStudioScreen
import com.example.ui.coloring.ColoringStudioViewModel
import com.example.ui.components.ParentalGateDialog
import com.example.ui.components.ScreenTimeBreakDialog
import com.example.ui.home.HomeScreen
import com.example.ui.parent.ParentZoneScreen
import com.example.ui.rewards.RewardsScreen
import com.example.ui.settings.SettingsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.utils.SoundManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var screenTimeManager: ScreenTimeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Core Infrastructure & Repositories
        val database = AppDatabase.getDatabase(applicationContext)
        val soundManager = SoundManager(lifecycleScope)
        val coloringRepo = ColoringRepository(database.recentPageDao())
        val artworkRepo = ArtworkRepository(applicationContext, database.artworkDao())
        val rewardsRepo = RewardsRepository(database.rewardDao())

        screenTimeManager = ScreenTimeManager(applicationContext, lifecycleScope)
        soundManager.isEnabled = screenTimeManager.settings.value.soundEnabled

        // Pre-warm thumbnail cache in background so browsing pages has instant 60fps scrolling
        lifecycleScope.launch(Dispatchers.Default) {
            ThumbnailCache.preloadThumbnails(coloringRepo.getAllPages().map { it.templateId }, 256)
        }

        val studioViewModel = ColoringStudioViewModel(
            application = application,
            coloringRepository = coloringRepo,
            artworkRepository = artworkRepo,
            rewardsRepository = rewardsRepo,
            soundManager = soundManager
        )

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                // State flows
                val userStats by rewardsRepo.userStatsFlow.collectAsState(initial = UserStatsEntity())
                val recentPages by coloringRepo.getRecentPagesFlow().collectAsState(initial = emptyList())
                val savedArtworks by artworkRepo.allArtworksFlow.collectAsState(initial = emptyList())
                val catalogRewards by rewardsRepo.rewardsWithStatusFlow.collectAsState(initial = rewardsRepo.allCatalogRewards)

                val parentSettings by screenTimeManager.settings.collectAsState()
                val usageSecondsToday by screenTimeManager.usageSecondsToday.collectAsState()
                val isLimitReached by screenTimeManager.isLimitReached.collectAsState()

                var showParentalGate by remember { mutableStateOf(false) }
                var onGateSuccessAction by remember { mutableStateOf<(() -> Unit)?>(null) }

                val dailyPage = remember { coloringRepo.getDailyFeaturedPage() }

                fun openGatedAction(action: () -> Unit) {
                    onGateSuccessAction = action
                    showParentalGate = true
                }

                fun startColoring(page: ColoringPage) {
                    try {
                        studioViewModel.initializeStudio(page)
                        navController.navigate("studio/${page.id}")
                    } catch (t: Throwable) {
                        android.util.Log.e("MainActivity", "Error navigating to ColoringStudio", t)
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.fillMaxSize(),
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)
                        )
                    }
                ) {
                    // 1. Home Screen
                    composable("home") {
                        HomeScreen(
                            userStats = userStats,
                            recentPages = recentPages,
                            dailyPage = dailyPage,
                            onCategoryClick = { category ->
                                navController.navigate("category/${category.id}")
                            },
                            onSelectPage = { page ->
                                startColoring(page)
                            },
                            onMyArtworkClick = {
                                navController.navigate("my_artwork")
                            },
                            onRewardsClick = {
                                navController.navigate("rewards")
                            },
                            onParentZoneClick = {
                                openGatedAction {
                                    navController.navigate("parent_zone")
                                }
                            },
                            onSettingsClick = {
                                navController.navigate("settings")
                            }
                        )
                    }

                    // 2. Category Screen / All Coloring Pages Gallery
                    composable(
                        route = "category/{categoryId}",
                        arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
                        val isAllCategory = categoryId.equals("all", ignoreCase = true) ||
                                categoryId.equals("all_pages", ignoreCase = true) ||
                                categoryId.equals("gallery", ignoreCase = true)
                        val category = if (isAllCategory) Category.ALL else Category.fromId(categoryId)
                        val pages = remember(category) { coloringRepo.getPagesByCategory(category) }

                        CategoryScreen(
                            category = category,
                            pages = pages,
                            onSelectPage = { page ->
                                startColoring(page)
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 3. Coloring Studio Screen
                    composable(
                        route = "studio/{pageId}",
                        arguments = listOf(navArgument("pageId") { type = NavType.StringType })
                    ) {
                        ColoringStudioScreen(
                            viewModel = studioViewModel,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onViewGallery = {
                                navController.navigate("my_artwork") {
                                    popUpTo("home")
                                }
                            },
                            onColorAnother = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 5. My Artwork Gallery Screen
                    composable("my_artwork") {
                        MyArtworkScreen(
                            artworks = savedArtworks,
                            onDeleteArtwork = { artwork ->
                                lifecycleScope.launch {
                                    artworkRepo.deleteArtwork(artwork)
                                }
                            },
                            onColorNewClick = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 6. Rewards Screen
                    composable("rewards") {
                        RewardsScreen(
                            userStats = userStats,
                            rewards = catalogRewards,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 7. Parent Zone Screen
                    composable("parent_zone") {
                        ParentZoneScreen(
                            parentSettings = parentSettings,
                            usageSecondsToday = usageSecondsToday,
                            onUpdateSettings = { updated ->
                                screenTimeManager.updateSettings(updated)
                                soundManager.isEnabled = updated.soundEnabled
                            },
                            onClearAllData = {
                                lifecycleScope.launch {
                                    artworkRepo.deleteAllArtworks()
                                    rewardsRepo.resetAllStats()
                                    coloringRepo.clearRecentPages()
                                    screenTimeManager.resetUsageData()
                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 8. Settings Screen
                    composable("settings") {
                        SettingsScreen(
                            soundEnabled = parentSettings.soundEnabled,
                            onToggleSound = { enabled ->
                                val updated = parentSettings.copy(soundEnabled = enabled)
                                screenTimeManager.updateSettings(updated)
                                soundManager.isEnabled = enabled
                            },
                            onOpenParentZone = {
                                openGatedAction {
                                    navController.navigate("parent_zone")
                                }
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

                // Parental Gate Dialog
                if (showParentalGate) {
                    ParentalGateDialog(
                        onDismiss = {
                            showParentalGate = false
                            onGateSuccessAction = null
                        },
                        onSuccess = {
                            showParentalGate = false
                            onGateSuccessAction?.invoke()
                            onGateSuccessAction = null
                        }
                    )
                }

                // Screen Time Play Limit Dialog
                if (isLimitReached && parentSettings.screenTimeLimitMinutes > 0) {
                    ScreenTimeBreakDialog(
                        limitMinutes = parentSettings.screenTimeLimitMinutes,
                        onTakeBreak = {
                            screenTimeManager.dismissReminderForToday()
                        },
                        onAddExtraTime = { extraMins ->
                            screenTimeManager.addExtraTimeMinutes(extraMins)
                        },
                        onDismissToday = {
                            screenTimeManager.dismissReminderForToday()
                        },
                        onOpenParentZone = {
                            screenTimeManager.dismissReminderForToday()
                            navController.navigate("parent_zone")
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (::screenTimeManager.isInitialized) {
            screenTimeManager.onAppForegrounded()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::screenTimeManager.isInitialized) {
            screenTimeManager.onAppBackgrounded()
        }
    }
}
