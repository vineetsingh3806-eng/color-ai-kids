package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = WarmOrange,
    onPrimary = Color.White,
    primaryContainer = PalePeach,
    onPrimaryContainer = TextPrimaryDark,
    secondary = SkyBlue,
    onSecondary = Color.White,
    secondaryContainer = PaleBlue,
    onSecondaryContainer = TextPrimaryDark,
    tertiary = GrassGreen,
    onTertiary = Color.White,
    tertiaryContainer = PaleMint,
    onTertiaryContainer = TextPrimaryDark,
    background = SurfaceWarm,
    onBackground = TextPrimaryDark,
    surface = Color.White,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceContainerWarm,
    onSurfaceVariant = TextSecondaryDark,
    outline = OutlineDark
)

private val DarkColorScheme = darkColorScheme(
    primary = WarmOrange,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF4E2600),
    onPrimaryContainer = PalePeach,
    secondary = SkyBlue,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF003650),
    onSecondaryContainer = PaleBlue,
    tertiary = GrassGreen,
    onTertiary = Color.Black,
    background = Color(0xFF1E1E24),
    surface = Color(0xFF282832),
    onBackground = Color(0xFFF0F0F0),
    onSurface = Color(0xFFF0F0F0)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our handcrafted joyful colors for children's app consistency
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
