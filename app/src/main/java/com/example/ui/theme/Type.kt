package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

enum class AppFontFamily(val displayName: String) {
    SOLAIMAN_LIPI("SolaimanLipi (সোলয়মানলিপি)"),
    KALPURUSH("Kalpurush (কালপুরুষ)"),
    NOTO_SERIF("Noto Serif Bengali (নোটো সেরিফ)")
}

fun getBengaliFontFamily(fontOption: AppFontFamily): FontFamily {
    return when (fontOption) {
        AppFontFamily.SOLAIMAN_LIPI -> FontFamily.SansSerif
        AppFontFamily.KALPURUSH -> FontFamily.Serif
        AppFontFamily.NOTO_SERIF -> FontFamily.Cursive
    }
}

fun getAppTypography(fontOption: AppFontFamily = AppFontFamily.SOLAIMAN_LIPI, scale: Float = 1.0f): Typography {
    val family = getBengaliFontFamily(fontOption)
    return Typography(
        headlineLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Bold,
            fontSize = (28 * scale).sp,
            lineHeight = (36 * scale).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Bold,
            fontSize = (22 * scale).sp,
            lineHeight = (28 * scale).sp
        ),
        titleLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Bold,
            fontSize = (18 * scale).sp,
            lineHeight = (24 * scale).sp
        ),
        titleMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = (16 * scale).sp,
            lineHeight = (22 * scale).sp
        ),
        bodyLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = (16 * scale).sp,
            lineHeight = (24 * scale).sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = (14 * scale).sp,
            lineHeight = (20 * scale).sp
        ),
        labelLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = (14 * scale).sp,
            lineHeight = (20 * scale).sp
        )
    )
}

val Typography = getAppTypography()

