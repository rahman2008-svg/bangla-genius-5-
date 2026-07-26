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

private val DarkColorScheme = darkColorScheme(
    primary = IndigoLight,
    onPrimary = Color.Black,
    primaryContainer = IndigoDark,
    onPrimaryContainer = IndigoContainer,
    secondary = AmberAccent,
    onSecondary = Color.Black,
    secondaryContainer = AmberDark,
    onSecondaryContainer = AmberContainer,
    tertiary = EmeraldAccent,
    onTertiary = Color.Black,
    tertiaryContainer = EmeraldDark,
    onTertiaryContainer = EmeraldContainer,
    background = VibrantBackgroundDark,
    onBackground = Color.White,
    surface = VibrantSurfaceDark,
    onSurface = Color.White,
    surfaceVariant = VibrantCardDark,
    onSurfaceVariant = Color(0xFFCBD5E1),
    error = RedError
)

private val LightColorScheme = lightColorScheme(
    primary = IndigoPrimary,
    onPrimary = Color.White,
    primaryContainer = IndigoContainer,
    onPrimaryContainer = OnIndigoContainer,
    secondary = AmberAccent,
    onSecondary = Color.White,
    secondaryContainer = AmberContainer,
    onSecondaryContainer = OnAmberContainer,
    tertiary = EmeraldAccent,
    onTertiary = Color.White,
    tertiaryContainer = EmeraldContainer,
    onTertiaryContainer = OnEmeraldContainer,
    background = VibrantBackgroundLight,
    onBackground = Color(0xFF0F172A),
    surface = VibrantSurfaceLight,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = VibrantCardLight,
    onSurfaceVariant = Color(0xFF475569),
    error = RedError
)

@Composable
fun BanglaGeniusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    fontFamily: AppFontFamily = AppFontFamily.SOLAIMAN_LIPI,
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = getAppTypography(fontOption = fontFamily, scale = fontScale)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}

