package com.adevinta.design.system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 7.toSp(),
        lineHeight = TextUnit(40f, TextUnitType.Sp)
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 7.toSp(),
        lineHeight = TextUnit(value = 32f, type = TextUnitType.Sp)
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 6.toSp(),
        lineHeight = TextUnit(28f, TextUnitType.Sp)
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 4.toSp(),
        lineHeight = TextUnit(value = 24f, type = TextUnitType.Sp)
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 3.toSp(),
        lineHeight = TextUnit(20f, TextUnitType.Sp)
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 2.toSp(),
        lineHeight = TextUnit(16f, TextUnitType.Sp)
    )
)

// CUSTOM Typo
val Typography.buttonLarge: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 4.toSp(),
        lineHeight = TextUnit(24f, TextUnitType.Sp)
    )