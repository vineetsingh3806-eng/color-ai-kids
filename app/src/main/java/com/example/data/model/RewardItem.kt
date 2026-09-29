package com.example.data.model

enum class RewardType {
    CRAYON_PACK,
    BACKGROUND,
    CHARACTER,
    SPECIAL_PACK
}

data class RewardItem(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String,
    val starsRequired: Int,
    val isUnlocked: Boolean,
    val type: RewardType
)
