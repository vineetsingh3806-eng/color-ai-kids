package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TextPrimaryDark

@Composable
fun KidButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconEmoji: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    testTag: String = "kid_button",
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.94f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f),
        label = "button_scale"
    )

    Surface(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = if (isPressed || !enabled) 2.dp else 6.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = backgroundColor.copy(alpha = 0.5f)
            )
            .testTag(testTag),
        shape = RoundedCornerShape(24.dp),
        color = if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.4f),
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 56.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick
                )
                .padding(horizontal = 24.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconEmoji != null) {
                Text(
                    text = iconEmoji,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = contentColor
            )
        }
    }
}
