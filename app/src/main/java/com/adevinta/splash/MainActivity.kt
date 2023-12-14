package com.adevinta.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.adevinta.HomeNavigationRoutesManagement
import com.adevinta.MainNavigation
import com.adevinta.design.system.theme.LBCTechTestTheme
import com.adevinta.design.system.utils.LocalWindowSizeProvider
import com.adevinta.design.system.utils.WindowSizeProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // define the first target of navigation, we can manage different target if needed
        setContent {
            setLBCNavigation(
                destination = HomeNavigationRoutesManagement.NavigationScreen.HOME.route
            )
        }
    }

    // Just one destination in this case
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    private fun setLBCNavigation(destination: String) {
        this.setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            WindowCompat.setDecorFitsSystemWindows(window, false)
            CompositionLocalProvider(
                LocalWindowSizeProvider provides WindowSizeProvider(windowSizeClass)
            ) {
                LBCTechTestTheme { MainNavigation(destination = destination) }
            }
        }
    }
}
