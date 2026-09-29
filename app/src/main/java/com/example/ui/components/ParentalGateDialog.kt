package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.TextPrimaryDark
import com.example.ui.theme.WarmOrange
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun ParentalGateDialog(
    onDismiss: () -> Unit,
    onSuccess: () -> Unit,
    initialNum1: Int? = null,
    initialNum2: Int? = null
) {
    // Generate a simple math problem that requires adult comprehension
    var questionVersion by remember { mutableIntStateOf(0) }
    val num1 = remember(questionVersion) {
        if (questionVersion == 0 && initialNum1 != null) initialNum1 else Random.nextInt(6, 12)
    }
    val num2 = remember(questionVersion) {
        if (questionVersion == 0 && initialNum2 != null) initialNum2 else Random.nextInt(7, 14)
    }
    val correctAnswer = remember(questionVersion) { num1 * num2 }

    var inputAnswer by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    var remainingSeconds by remember {
        mutableIntStateOf(ParentalGateSecurity.remainingCooldownSeconds())
    }
    val isCoolingDown = remainingSeconds > 0

    // Ticking countdown effect when cooldown is active
    LaunchedEffect(isCoolingDown) {
        if (isCoolingDown) {
            while (true) {
                val remaining = ParentalGateSecurity.remainingCooldownSeconds()
                remainingSeconds = remaining
                if (remaining <= 0) {
                    ParentalGateSecurity.checkAndResetIfExpired()
                    questionVersion++
                    inputAnswer = ""
                    isError = false
                    break
                }
                delay(250L)
            }
        }
    }

    val handleAnswerSubmit = {
        if (!ParentalGateSecurity.isCoolingDown()) {
            if (inputAnswer.trim() == correctAnswer.toString()) {
                ParentalGateSecurity.recordSuccess()
                onSuccess()
            } else {
                isError = true
                val triggeredCooldown = ParentalGateSecurity.recordFailedAttempt()
                if (triggeredCooldown) {
                    remainingSeconds = ParentalGateSecurity.remainingCooldownSeconds()
                }
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("parental_gate_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🔒",
                    fontSize = 44.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Grown-Ups Only",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    color = TextPrimaryDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Please ask a parent or adult to solve this to enter the Parent Zone:",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFF3F4F6),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "$num1 × $num2 = ?",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isCoolingDown) Color.Gray else WarmOrange
                        ),
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                            .testTag("parental_gate_question")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = inputAnswer,
                    onValueChange = {
                        if (!isCoolingDown) {
                            inputAnswer = it
                            isError = false
                        }
                    },
                    label = { Text(if (isCoolingDown) "Cooldown active" else "Enter answer") },
                    isError = isError && !isCoolingDown,
                    enabled = !isCoolingDown,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        handleAnswerSubmit()
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("parental_gate_input")
                )

                if (isCoolingDown) {
                    Text(
                        text = "Too many attempts. Please wait $remainingSeconds seconds before trying again.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .testTag("parental_gate_cooldown_message")
                    )
                } else if (isError) {
                    Text(
                        text = "Incorrect answer, please try again",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = handleAnswerSubmit,
                        enabled = !isCoolingDown,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("parental_gate_submit"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WarmOrange)
                    ) {
                        Text(if (isCoolingDown) "Wait ($remainingSeconds s)" else "Enter")
                    }
                }
            }
        }
    }
}
