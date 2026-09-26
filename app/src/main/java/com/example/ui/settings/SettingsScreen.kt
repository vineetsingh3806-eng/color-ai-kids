package com.example.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Info
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
import com.example.ui.components.KidCard
import com.example.ui.theme.*

@Composable
fun SettingsScreen(
    soundEnabled: Boolean,
    onToggleSound: (Boolean) -> Unit,
    onOpenParentZone: () -> Unit,
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
                            .testTag("settings_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "⚙️ Settings",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sound Effects Card
            KidCard(backgroundColor = Color.White) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = null,
                            tint = WarmOrange,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Sound Effects",
                                fontWeight = FontWeight.Bold,
                                color = TextPrimaryDark
                            )
                            Text(
                                text = "Fun taps, chimes & celebration sounds",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondaryDark
                            )
                        }
                    }

                    Switch(
                        checked = soundEnabled,
                        onCheckedChange = onToggleSound,
                        modifier = Modifier.testTag("sound_switch")
                    )
                }
            }

            // Parent Zone Shortcut
            KidCard(
                backgroundColor = PalePeach,
                borderColor = WarmOrange.copy(alpha = 0.4f),
                onClick = onOpenParentZone,
                testTag = "settings_parent_zone_card"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = WarmOrange,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Parent Zone & Safety",
                                fontWeight = FontWeight.Bold,
                                color = TextPrimaryDark
                            )
                            Text(
                                text = "Screen time, sound & privacy",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondaryDark
                            )
                        }
                    }

                    Text(text = "Enter ▶", color = WarmOrange, fontWeight = FontWeight.Bold)
                }
            }

            // About ColorAI Kids
            KidCard(backgroundColor = Color.White) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = SkyBlue,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "About ColorAI Kids",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Version 1.0.0 (Production Build)",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = TextPrimaryDark
                    )
                    Text(
                        text = "Designed for children aged 3-8. Built with modern Android Jetpack Compose, Material Design 3, and offline-first data persistence.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondaryDark,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
