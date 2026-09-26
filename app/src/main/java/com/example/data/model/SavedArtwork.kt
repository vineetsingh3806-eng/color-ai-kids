package com.example.data.model

data class SavedArtwork(
    val id: String,
    val title: String,
    val categoryId: String,
    val imageFilePath: String,
    val templateId: String,
    val createdAt: Long,
    val isCompleted: Boolean = true,
    val starsAwarded: Int = 10
)
