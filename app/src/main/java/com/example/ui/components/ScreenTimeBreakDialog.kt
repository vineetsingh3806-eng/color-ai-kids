package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.WarmOrange

@Composable
fun ScreenTimeBreakDialog(
    limitMinutes: Int,
    onTakeBreak: () -> Unit,
    onAddExtraTime: (Int) -> Unit,
    onDismissToday: () -> Unit,
    onOpenParentZone: () -> Unit
) {
    var showParentalGate by remember { mutableStateOf(false) }
    var showGrownUpOptions by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("screen_time_break_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⏱️",
                    fontSize = 54.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Time for a Break!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    ),
                    color = TextPrimaryDark
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "You've reached your $limitMinutes minute coloring play limit for today! Great coloring! Time to rest your eyes, stretch, and play.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onTakeBreak,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("screen_time_take_break_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = WarmOrange)
                ) {
                    Text(
                        text = "🌟 Take a Break",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { showParentalGate = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("screen_time_parent_unlock_button"),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "🔒 Grown-Up Options",
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimaryDark
                    )
                }
            }
        }
    }

    if (showParentalGate) {
        ParentalGateDialog(
            onDismiss = { showParentalGate = false },
            onSuccess = {
                showParentalGate = false
                showGrownUpOptions = true
            }
        )
    }

    if (showGrownUpOptions) {
        AlertDialog(
            onDismissRequest = { showGrownUpOptions = false },
            title = {
                Text(text = "Parent Screen Time Controls", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Select an option for today's coloring session:")
                    
                    OutlinedButton(
                        onClick = {
                            showGrownUpOptions = false
                            onAddExtraTime(15)
                        },
                        modifier = Modifier.fillMaxWidth().testTag("add_15_mins_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("+ 15 More Minutes")
                    }

                    OutlinedButton(
                        onClick = {
                            showGrownUpOptions = false
                            onDismissToday()
                        },
                        modifier = Modifier.fillMaxWidth().testTag("dismiss_today_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Dismiss Limit for Today")
                    }

                    Button(
                        onClick = {
                            showGrownUpOptions = false
                            onOpenParentZone()
                        },
                        modifier = Modifier.fillMaxWidth().testTag("open_parent_zone_from_break_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = WarmOrange),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Change Settings in Parent Zone")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showGrownUpOptions = false }) {
                    Text("Close")
                }
            },
            shape = RoundedCornerShape(24.dp)
        )
    }
}
