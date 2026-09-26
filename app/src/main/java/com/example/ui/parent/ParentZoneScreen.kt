package com.example.ui.parent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ParentSettings
import com.example.ui.components.KidCard
import com.example.ui.theme.*

@Composable
fun ParentZoneScreen(
    parentSettings: ParentSettings,
    usageSecondsToday: Long = 0L,
    onUpdateSettings: (ParentSettings) -> Unit,
    onClearAllData: () -> Unit,
    onBackClick: () -> Unit
) {
    var showResetDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }

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
                            .testTag("parent_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "🔒 Parent Zone",
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
            // Safety & Trust Badge
            KidCard(
                backgroundColor = PaleMint,
                borderColor = GrassGreen.copy(alpha = 0.4f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = GrassGreen,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Google Play Families & COPPA Compliant",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimaryDark
                        )
                        Text(
                            text = "Zero Data Tracking • Safe Local Storage",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondaryDark
                        )
                    }
                }
            }

            // Screen Time Timer Controls
            KidCard(backgroundColor = Color.White) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "⏱️ Screen Time Play Limit",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = TextPrimaryDark
                    )
                    Text(
                        text = "Set a friendly reminder when your child reaches their coloring limit.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondaryDark,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                    )

                    // Active tracking feedback
                    val usedMins = (usageSecondsToday / 60).toInt()
                    val statusText = if (parentSettings.screenTimeLimitMinutes == 0) {
                        "Status: Timer is Off (No play limit)"
                    } else {
                        "Status: Active limit of ${parentSettings.screenTimeLimitMinutes} min ($usedMins min used today)"
                    }
                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                        color = if (parentSettings.screenTimeLimitMinutes > 0) WarmOrange else TextSecondaryDark,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    val limits = listOf(0 to "Off", 15 to "15 min", 30 to "30 min", 45 to "45 min", 60 to "60 min")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        limits.forEach { (mins, label) ->
                            val isSelected = parentSettings.screenTimeLimitMinutes == mins
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) WarmOrange else Color(0xFFF2F3F5),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        onUpdateSettings(parentSettings.copy(screenTimeLimitMinutes = mins))
                                    }
                                    .testTag("screen_time_limit_$mins")
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 10.dp)
                                ) {
                                    Text(
                                        text = label,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = if (isSelected) Color.White else TextPrimaryDark
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Sound Effects Setting
            KidCard(backgroundColor = Color.White) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "🔊 Sound Effects",
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimaryDark
                        )
                        Text(
                            text = "Cheerful chimes, tap sounds, and celebration music",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondaryDark
                        )
                    }
                    Switch(
                        checked = parentSettings.soundEnabled,
                        onCheckedChange = {
                            onUpdateSettings(parentSettings.copy(soundEnabled = it))
                        },
                        modifier = Modifier.testTag("sound_toggle_switch")
                    )
                }
            }

            // Privacy Policy & Information
            KidCard(
                backgroundColor = Color.White,
                onClick = { showPrivacyDialog = true },
                testTag = "parent_privacy_policy_card"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "📜 Children's Privacy Policy",
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimaryDark
                        )
                        Text(
                            text = "Read our COPPA and GDPR-K privacy disclosure",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondaryDark
                        )
                    }
                    Text(text = "View ▶", color = WarmOrange, fontWeight = FontWeight.Bold)
                }
            }

            // Danger Zone: Reset Data
            KidCard(
                backgroundColor = Color(0xFFFFF1F0),
                borderColor = CoralRed.copy(alpha = 0.3f)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "🗑️ Clear Drawings & Reset Progress",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = CoralRed
                    )
                    Text(
                        text = "Deletes all locally saved coloring pages and resets stars back to initial state.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondaryDark,
                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                    )

                    Button(
                        onClick = { showResetDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = CoralRed),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth().testTag("parent_clear_all_button")
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Clear All Data")
                    }
                }
            }
        }

        // Privacy Policy Dialog
        if (showPrivacyDialog) {
            AlertDialog(
                onDismissRequest = { showPrivacyDialog = false },
                title = { Text(text = "🛡️ Children's Privacy Policy", fontWeight = FontWeight.Bold) },
                text = {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = """
                            ColorAI Kids is built from the ground up for children's safety and privacy:

                            • Zero Personal Data Collected: We do not require, collect, store, or transmit any personally identifiable information (PII). No names, email addresses, or phone numbers are ever asked.
                            
                            • Local-First Artwork: All completed coloring pages are stored purely on your local device. Artworks are never uploaded to public servers.
                            
                            • Full Parental Control: Parents can set screen time limits, control sound effects, and clear all saved data at any time in this Parent Zone.
                            """.trimIndent(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showPrivacyDialog = false },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.testTag("privacy_policy_confirm_button")
                    ) {
                        Text("Understood")
                    }
                },
                shape = RoundedCornerShape(24.dp)
            )
        }

        // Reset Confirmation Dialog
        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { showResetDialog = false },
                title = { Text(text = "Are you sure?", fontWeight = FontWeight.Bold) },
                text = { Text("This will permanently delete all saved drawings and reset star rewards. This cannot be undone.") },
                confirmButton = {
                    Button(
                        onClick = {
                            onClearAllData()
                            showResetDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CoralRed),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.testTag("confirm_delete_everything_button")
                    ) {
                        Text("Yes, Delete Everything")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showResetDialog = false },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.testTag("cancel_delete_button")
                    ) {
                        Text("Cancel")
                    }
                },
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}
