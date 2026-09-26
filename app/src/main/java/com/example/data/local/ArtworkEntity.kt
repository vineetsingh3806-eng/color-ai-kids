package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_artworks")
data class ArtworkEntity(
    @PrimaryKey val id: String,
    val title: String,
    val categoryId: String,
    val imageFilePath: String,
    val templateId: String,
    val createdAt: Long,
    val isCompleted: Boolean,
    val starsAwarded: Int
)
