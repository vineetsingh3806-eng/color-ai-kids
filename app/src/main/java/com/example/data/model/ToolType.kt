package com.example.data.model

enum class ToolType(
    val title: String,
    val emoji: String,
    val defaultStrokeWidth: Float
) {
    FILL_BUCKET("Fill", "🪣", 0f),
    BRUSH("Brush", "🖌️", 28f),
    CRAYON("Crayon", "🖍️", 32f),
    ERASER("Eraser", "🧼", 48f)
}
