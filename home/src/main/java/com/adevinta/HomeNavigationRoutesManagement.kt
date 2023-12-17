package com.adevinta

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adevinta.home.albumlist.AlbumListStateful

class HomeNavigationRoutesManagement {
    fun homeNavigationManagement(navGraphBuilder: NavGraphBuilder) {
        with(navGraphBuilder) {
            composable(route = NavigationScreen.HOME.route) { AlbumListStateful() }
        }
    }

    sealed interface NavigationScreen {
        object HOME {
            const val route = "route_album_list"
        }
    }
}
