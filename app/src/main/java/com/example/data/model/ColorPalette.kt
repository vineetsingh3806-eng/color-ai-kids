package com.example.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.ui.theme.*

data class CrayonColor(
    val id: String,
    val name: String,
    val color: Color,
    val isGlitter: Boolean = false,
    val requiresUnlock: Boolean = false,
    val unlocked: Boolean = true
) {
    val colorInt: Int get() = color.toArgb()
}

object PredefinedPalettes {
    val primaryCrayons = listOf(
        CrayonColor("red", "Sunny Red", CrayonRed),
        CrayonColor("orange", "Orange", CrayonOrange),
        CrayonColor("yellow", "Yellow", CrayonYellow),
        CrayonColor("green", "Green", CrayonGreen),
        CrayonColor("teal", "Teal", CrayonTeal),
        CrayonColor("blue", "Sky Blue", CrayonBlue),
        CrayonColor("purple", "Purple", CrayonPurple),
        CrayonColor("pink", "Pink", CrayonPink),
        CrayonColor("peach", "Peach", CrayonPeach),
        CrayonColor("brown", "Brown", CrayonBrown),
        CrayonColor("black", "Black", CrayonBlack),
        CrayonColor("white", "White", CrayonWhite)
    )

    val pastelColors = listOf(
        CrayonColor("pastel_pink", "Baby Pink", BubblegumPink),
        CrayonColor("pastel_yellow", "Buttercup", SunshineYellow),
        CrayonColor("pastel_mint", "Minty", PaleMint),
        CrayonColor("pastel_blue", "Cloud Blue", PaleBlue),
        CrayonColor("pastel_lavender", "Lavender", PaleLavender),
        CrayonColor("pastel_peach", "Apricot", PalePeach)
    )

    val glitterColors = listOf(
        CrayonColor("glitter_gold", "Sparkle Gold", CrayonGoldSparkle, isGlitter = true),
        CrayonColor("glitter_rainbow", "Rainbow Glow", CrayonRainbowSparkle, isGlitter = true),
        CrayonColor("glitter_coral", "Star Coral", CoralRed, isGlitter = true),
        CrayonColor("glitter_sun", "Sunshine", SunshineYellow, isGlitter = true)
    )
}
