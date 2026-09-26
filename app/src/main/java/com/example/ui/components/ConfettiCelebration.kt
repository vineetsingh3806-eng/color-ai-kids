package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*
import kotlin.random.Random

private data class Particle(
    val initialX: Float,
    val initialY: Float,
    val speedX: Float,
    val speedY: Float,
    val color: Color,
    val size: Float,
    val rotationSpeed: Float
)

@Composable
fun ConfettiCelebration(
    modifier: Modifier = Modifier,
    particleCount: Int = 45
) {
    val colors = listOf(
        SunshineYellow, WarmOrange, CoralRed, SkyBlue, GrassGreen,
        BubblegumPink, LavenderPurple, CrayonGoldSparkle
    )

    val particles = remember {
        List(particleCount) {
            Particle(
                initialX = Random.nextFloat(),
                initialY = Random.nextFloat() * 0.3f,
                speedX = (Random.nextFloat() - 0.5f) * 0.4f,
                speedY = Random.nextFloat() * 0.6f + 0.3f,
                color = colors[Random.nextInt(colors.size)],
                size = Random.nextFloat() * 12f + 8f,
                rotationSpeed = (Random.nextFloat() - 0.5f) * 10f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "confetti_transition")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "confetti_progress"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        for (p in particles) {
            val currentY = ((p.initialY + p.speedY * progress) % 1.2f) * h
            val currentX = (p.initialX + p.speedX * progress * 0.8f).coerceIn(0f, 1f) * w

            drawRect(
                color = p.color,
                topLeft = Offset(currentX, currentY),
                size = Size(p.size, p.size * 0.6f)
            )
        }
    }
}
