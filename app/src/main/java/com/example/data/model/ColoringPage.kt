package com.example.data.model

data class ColoringPage(
    val id: String,
    val title: String,
    val category: Category,
    val emoji: String,
    val description: String,
    val templateId: String = id,
    val difficulty: String = "Easy" // "Easy", "Medium"
)
