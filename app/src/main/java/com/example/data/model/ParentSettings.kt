package com.example.data.model

data class ParentSettings(
    val soundEnabled: Boolean = true,
    val screenTimeLimitMinutes: Int = 0,
    val palmRejectionEnabled: Boolean = true,
    val isFamilyPremium: Boolean = false
)
