package com.adevinta.design.system.utils

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

// ProvidableCompositionLocal for WindowSizeProvider, to be provided at the top level of the
// composition
val LocalWindowSizeProvider: ProvidableCompositionLocal<WindowSizeProvider> =
    staticCompositionLocalOf {
        // Error message to ensure LocalWindowSizeProvider is provided before usage
        error("LocalWindowSizeProvider not provided")
    }

// Class to provide window size information, particularly for determining orientation
class WindowSizeProvider(private val windowSize: WindowSizeClass) {
    // Function to determine if the device is in portrait mode
    fun isPortrait(): Boolean = windowSize.widthSizeClass == WindowWidthSizeClass.Compact
}

@Composable
fun ProvideWindowSizeProvider(windowSize: WindowSizeClass, content: @Composable () -> Unit) {
    // Remembering the WindowSizeProvider instance to avoid recreation on recomposition
    val provider = remember { WindowSizeProvider(windowSize) }

    // Providing the WindowSizeProvider to the Composable tree using CompositionLocalProvider
    CompositionLocalProvider(LocalWindowSizeProvider provides provider) { content() }
}
