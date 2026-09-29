package com.example.ui.rewards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserStatsEntity
import com.example.data.model.RewardItem
import com.example.ui.components.KidCard
import com.example.ui.theme.*

@Composable
fun RewardsScreen(
    userStats: UserStatsEntity,
    rewards: List<RewardItem>,
    onBackClick: () -> Unit
) {
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
                            .testTag("rewards_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "⭐ Star Rewards",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        ),
                        color = TextPrimaryDark
                    )
                }
            }
        },
        containerColor = SurfaceWarm
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Star Counter Hero Card
            item {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White,
                    shadowElevation = 4.dp,
                    border = androidx.compose.foundation.BorderStroke(2.dp, SunshineYellow),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "⭐", fontSize = 54.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${userStats.totalStars} Stars",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 32.sp
                            ),
                            color = TextPrimaryDark
                        )
                        Text(
                            text = "Color pages and save drawings to earn 10 stars every time!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryDark,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Next Reward Progress
                        val nextReward = rewards.firstOrNull { !it.isUnlocked }
                        if (nextReward != null) {
                            val progress = (userStats.totalStars.toFloat() / nextReward.starsRequired).coerceIn(0f, 1f)
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Next: ${nextReward.title}",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = TextPrimaryDark
                                    )
                                    Text(
                                        text = "${userStats.totalStars} / ${nextReward.starsRequired} ⭐",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = WarmOrange
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                LinearProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(12.dp)
                                        .clip(RoundedCornerShape(6.dp)),
                                    color = SunshineYellow,
                                    trackColor = Color(0xFFEEEEEE)
                                )
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = PaleMint,
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text(
                                    text = "🎉 All Rewards Unlocked! You are a Coloring Master!",
                                    fontWeight = FontWeight.Bold,
                                    color = GrassGreen,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Reward Items List
            items(rewards) { reward ->
                RewardRowCard(reward = reward, currentStars = userStats.totalStars)
            }
        }
    }
}

@Composable
private fun RewardRowCard(
    reward: RewardItem,
    currentStars: Int
) {
    val isUnlocked = reward.isUnlocked

    KidCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = if (isUnlocked) PaleMint else Color.White,
        borderColor = if (isUnlocked) GrassGreen.copy(alpha = 0.5f) else Color(0x1F000000),
        testTag = "reward_card_${reward.id}"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = if (isUnlocked) Color.White else Color(0xFFF3F4F6),
                modifier = Modifier.size(60.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = reward.iconEmoji, fontSize = 32.sp)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = reward.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                    color = TextPrimaryDark
                )
                Text(
                    text = reward.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryDark,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            if (isUnlocked) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = GrassGreen,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Unlocked",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            } else {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFEEEEEE),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${reward.starsRequired} ⭐",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}
