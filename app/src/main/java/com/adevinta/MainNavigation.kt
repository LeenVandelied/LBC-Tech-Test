package com.adevinta

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun MainNavigation(destination: String) {
    val navController = rememberNavController()
    val homeNavigationRoutesManagement = HomeNavigationRoutesManagement()

    NavHost(navController = navController, startDestination = destination) {
        homeNavigationRoutesManagement.homeNavigationManagement(navGraphBuilder = this)
    }
}
