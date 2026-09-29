package com.example.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.domain.coloring.ThumbnailCache
import com.example.ui.theme.SurfaceWarm
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.TextSecondaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CategoryScreen(
    category: Category,
    pages: List<ColoringPage>,
    onSelectPage: (ColoringPage) -> Unit,
    onBackClick: () -> Unit
) {
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    // Preload thumbnails in the background so scrolling through 220+ items has 0 lag
    LaunchedEffect(pages) {
        withContext(Dispatchers.IO) {
            ThumbnailCache.preloadThumbnails(pages.map { it.templateId }, 256)
        }
    }

    Scaffold(
        topBar = {
            Surface(
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF0F0F0))
                            .testTag("category_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "${category.emoji} ${category.title}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            ),
                            color = TextPrimaryDark
                        )
                        Text(
                            text = "${pages.size} Coloring Pages 🎨",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            color = TextSecondaryDark
                        )
                    }
                }
            }
        },
        containerColor = SurfaceWarm
    ) { innerPadding ->
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 12.dp,
                bottom = innerPadding.calculateBottomPadding() + 64.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("category_pages_grid")
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
                            if (down.type == PointerType.Mouse) {
                                val velocityTracker = VelocityTracker()
                                velocityTracker.addPosition(down.uptimeMillis, down.position)
                                var dragConsumed = false
                                var totalDrag = 0f
                                val touchSlop = viewConfiguration.touchSlop
                                while (true) {
                                    val event = awaitPointerEvent(PointerEventPass.Initial)
                                    val change = event.changes.firstOrNull { it.id == down.id } ?: break
                                    velocityTracker.addPosition(change.uptimeMillis, change.position)
                                    if (!change.pressed) {
                                        if (dragConsumed) {
                                            val velocityY = velocityTracker.calculateVelocity().y
                                            if (kotlin.math.abs(velocityY) > 100f) {
                                                coroutineScope.launch {
                                                    gridState.animateScrollBy(-velocityY * 0.4f)
                                                }
                                            }
                                        }
                                        break
                                    }
                                    val delta = change.position.y - change.previousPosition.y
                                    totalDrag += delta
                                    if (!dragConsumed && kotlin.math.abs(totalDrag) > touchSlop) {
                                        dragConsumed = true
                                    }
                                    if (dragConsumed && delta != 0f) {
                                        change.consume()
                                        // Synchronously dispatch scroll delta without mutex contention or launching coroutines
                                        gridState.dispatchRawDelta(-delta)
                                    }
                                }
                            }
                        }
                    }
                }
        ) {
            items(
                items = pages,
                key = { it.id },
                contentType = { "coloring_page" }
            ) { page ->
                val onCardClick = remember(page.id) { { onSelectPage(page) } }
                PageGridCard(
                    page = page,
                    onClick = onCardClick
                )
            }
        }
    }
}

@Composable
private fun PageGridCard(
    page: ColoringPage,
    onClick: () -> Unit
) {
    val cachedImage = remember(page.templateId) { ThumbnailCache.getCachedImageBitmap(page.templateId, 256) }
    val thumbnailBitmap by produceState<ImageBitmap?>(initialValue = cachedImage, page.templateId) {
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
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("page_card_${page.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val img = thumbnailBitmap
                    if (img != null) {
                        Image(
                            bitmap = img,
                            contentDescription = page.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    } else {
                        Text(text = page.emoji, fontSize = 54.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = page.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                ),
                color = TextPrimaryDark,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Text(
                text = "Tap to Color 🎨",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = TextSecondaryDark,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
