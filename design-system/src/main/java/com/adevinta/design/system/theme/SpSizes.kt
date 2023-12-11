package com.adevinta.design.system.theme

import androidx.compose.ui.unit.sp

internal fun Int.toSp() = when (this) {
    1 -> 10.sp
    2 -> 12.sp
    3 -> 14.sp
    4 -> 16.sp
    5 -> 18.sp
    6 -> 20.sp
    7 -> 24.sp
    8 -> 28.sp
    9 -> 32.sp
    10 -> 36.sp
    11 -> 40.sp
    12 -> 48.sp
    13 -> 56.sp
    14 -> 64.sp
    15 -> 72.sp
    else -> throw IllegalArgumentException()
}