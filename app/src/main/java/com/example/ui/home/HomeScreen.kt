package com.example.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserStatsEntity
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.domain.coloring.ThumbnailCache
import com.example.ui.components.KidButton
import com.example.ui.components.KidCard
import com.example.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(
    userStats: UserStatsEntity,
    recentPages: List<ColoringPage>,
    dailyPage: ColoringPage,
    onCategoryClick: (Category) -> Unit,
    onSelectPage: (ColoringPage) -> Unit,
    onMyArtworkClick: () -> Unit,
    onRewardsClick: () -> Unit,
    onParentZoneClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                stars = userStats.totalStars,
                onRewardsClick = onRewardsClick,
                onArtworkClick = onMyArtworkClick,
                onParentClick = onParentZoneClick,
                onSettingsClick = onSettingsClick
            )
        },
        containerColor = SurfaceWarm
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 36.dp)
        ) {
            // 1. Daily Coloring Challenge
            item {
                DailyColoringSection(
                    page = dailyPage,
                    onSelectPage = { onSelectPage(dailyPage) },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )
            }

            // 2. Continue Coloring (if any recent pages)
            if (recentPages.isNotEmpty()) {
                item {
                    ContinueColoringSection(
                        pages = recentPages,
                        onSelectPage = onSelectPage,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            // 3. "What do you want to color?" - Category Grid Header
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "What do you want to color?",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        ),
                        color = TextPrimaryDark
                    )
                    Text(
                        text = "Choose a fun category with lots of drawings!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryDark,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Category Cards in 2 columns
            val categories = Category.entries.toList()
            for (i in categories.indices step 2) {
                item(key = "category_row_${categories[i].id}", contentType = "category_row") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        CategoryCard(
                            category = categories[i],
                            onClick = { onCategoryClick(categories[i]) },
                            modifier = Modifier.weight(1f)
                        )
                        if (i + 1 < categories.size) {
                            CategoryCard(
                                category = categories[i + 1],
                                onClick = { onCategoryClick(categories[i + 1]) },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            // 5. My Collection & Quick Nav Footer
            item {
                RewardsQuickBanner(
                    stars = userStats.totalStars,
                    onRewardsClick = onRewardsClick,
                    onMyArtworkClick = onMyArtworkClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun HomeTopBar(
    stars: Int,
    onRewardsClick: () -> Unit,
    onArtworkClick: () -> Unit,
    onParentClick: () -> Unit,
    onSettingsClick: () -> Unit
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
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // App Title with icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("app_header")
            ) {
                Text(
                    text = "🎨",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Column {
                    Text(
                        text = "ColorAI Kids",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        ),
                        color = TextPrimaryDark
                    )
                    Text(
                        text = "Create • Color • Collect",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = WarmOrange
                        )
                    )
                }
            }

            // Action Pills: Stars, Gallery, Parent Zone, Settings
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Star Counter Pill
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = SunshineYellow.copy(alpha = 0.3f),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, SunshineYellow),
                    modifier = Modifier
                        .clickable(onClick = onRewardsClick)
                        .testTag("home_stars_pill")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "⭐", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$stars",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = TextPrimaryDark
                        )
                    }
                }

                // My Artwork Icon Button
                IconButton(
                    onClick = onArtworkClick,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(PaleBlue)
                        .testTag("home_artwork_button")
                ) {
                    Text(text = "🖼️", fontSize = 18.sp)
                }

                // Parent Zone Button
                IconButton(
                    onClick = onParentClick,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(PalePeach)
                        .testTag("home_parent_button")
                ) {
                    Text(text = "👨‍👩‍👧", fontSize = 18.sp)
                }

                // Settings Button
                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEEEEEE))
                        .testTag("home_settings_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Settings",
                        tint = TextPrimaryDark,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyColoringSection(
    page: ColoringPage,
    onSelectPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialImage = remember(page.templateId) { ThumbnailCache.getCachedImageBitmap(page.templateId, 256) }
    val dailyThumbnail by produceState<ImageBitmap?>(initialValue = initialImage, page.templateId) {
        if (value == null) {
            val immediate = ThumbnailCache.getCachedImageBitmap(page.templateId, 256)
            if (immediate != null) {
                value = immediate
            } else {
                value = withContext(Dispatchers.IO) {
                    ThumbnailCache.getImageBitmap(page.templateId, 256)
                }
            }
        }
    }

    KidCard(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = PaleMint,
        borderColor = GrassGreen.copy(alpha = 0.4f),
        onClick = onSelectPage,
        testTag = "daily_coloring_card"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD1D5DB)),
                modifier = Modifier.size(68.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val bmp = dailyThumbnail
                    if (bmp != null) {
                        Image(
                            bitmap = bmp,
                            contentDescription = page.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    } else {
                        Text(text = page.emoji, fontSize = 36.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GrassGreen,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "⭐ DAILY COLORING",
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = page.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = TextPrimaryDark
                )
                Text(
                    text = "Color today to earn +10 bonus stars!",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryDark
                )
            }

            Text(
                text = "▶",
                fontSize = 22.sp,
                color = GrassGreen,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ContinueColoringSection(
    pages: List<ColoringPage>,
    onSelectPage: (ColoringPage) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Continue Coloring",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            ),
            color = TextPrimaryDark,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(
                items = pages,
                key = { it.id },
                contentType = { "recent_page" }
            ) { page ->
                val onChipClick = remember(page.id) { { onSelectPage(page) } }
                RecentPageChip(
                    page = page,
                    onClick = onChipClick
                )
            }
        }
    }
}

@Composable
private fun RecentPageChip(
    page: ColoringPage,
    onClick: () -> Unit
) {
    val initialImage = remember(page.templateId) { ThumbnailCache.getCachedImageBitmap(page.templateId, 256) }
    val chipThumbnail by produceState<ImageBitmap?>(initialValue = initialImage, page.templateId) {
        if (value == null) {
            val immediate = ThumbnailCache.getCachedImageBitmap(page.templateId, 256)
            if (immediate != null) {
                value = immediate
            } else {
                value = withContext(Dispatchers.IO) {
                    ThumbnailCache.getImageBitmap(page.templateId, 256)
                }
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        modifier = Modifier
            .width(130.dp)
            .clickable(onClick = onClick)
            .testTag("recent_page_${page.id}")
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.size(60.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val bmp = chipThumbnail
                    if (bmp != null) {
                        Image(
                            bitmap = bmp,
                            contentDescription = page.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    } else {
                        Text(text = page.emoji, fontSize = 28.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = page.title,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimaryDark,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.94f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f),
        label = "cat_scale"
    )

    Surface(
        modifier = modifier
            .scale(scale)
            .height(130.dp)
            .shadow(3.dp, RoundedCornerShape(24.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .testTag("category_card_${category.id}"),
        shape = RoundedCornerShape(24.dp),
        color = category.tintColor,
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0x18000000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = category.emoji,
                fontSize = 38.sp
            )

            Column {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp
                    ),
                    color = TextPrimaryDark
                )
                Text(
                    text = "Tap to view",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondaryDark
                )
            }
        }
    }
}

@Composable
private fun RewardsQuickBanner(
    stars: Int,
    onRewardsClick: () -> Unit,
    onMyArtworkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KidCard(
        modifier = modifier,
        backgroundColor = Color.White,
        borderColor = SunshineYellow.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "🏆 My Coloring Journey",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                    color = TextPrimaryDark
                )
                Text(
                    text = "You have earned $stars stars! Collect more to unlock magical glitter crayons.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryDark,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onRewardsClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SunshineYellow),
                modifier = Modifier.testTag("home_view_rewards_button")
            ) {
                Text(
                    text = "View",
                    color = TextPrimaryDark,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

