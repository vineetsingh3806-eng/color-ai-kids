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
import kotlin.random.Random

@Composable
fun ParentalGateDialog(
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {
    // Generate a simple math problem that requires adult/older child comprehension
    val num1 = remember { Random.nextInt(6, 12) }
    val num2 = remember { Random.nextInt(7, 14) }
    val correctAnswer = remember { num1 * num2 }

    var inputAnswer by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

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
                            color = WarmOrange
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = inputAnswer,
                    onValueChange = {
                        inputAnswer = it
                        isError = false
                    },
                    label = { Text("Enter answer") },
                    isError = isError,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (inputAnswer.trim() == correctAnswer.toString()) {
                            onSuccess()
                        } else {
                            isError = true
                        }
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("parental_gate_input")
                )

                if (isError) {
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
                        onClick = {
                            if (inputAnswer.trim() == correctAnswer.toString()) {
                                onSuccess()
                            } else {
                                isError = true
                            }
                        },
                        modifier = Modifier.weight(1f).testTag("parental_gate_submit"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WarmOrange)
                    ) {
                        Text("Enter")
                    }
                }
            }
        }
    }
}
