package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun KidCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color(0x1F000000),
    onClick: (() -> Unit)? = null,
    testTag: String = "kid_card",
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed && onClick != null) 0.96f else 1.0f,
        animationSpec = spring(dampingRatio = 0.65f),
        label = "card_scale"
    )

    Surface(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = if (isPressed) 2.dp else 4.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = Color(0x22000000)
            )
            .testTag(testTag),
        shape = RoundedCornerShape(24.dp),
        color = backgroundColor,
        border = BorderStroke(2.dp, borderColor)
    ) {
        val clickModifier = if (onClick != null) {
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
        } else {
            Modifier
        }

        Box(modifier = clickModifier.padding(16.dp)) {
            content()
        }
    }
}
