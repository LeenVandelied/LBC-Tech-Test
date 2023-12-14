package com.adevinta.home.albumlist

internal sealed interface AlbumListActions

// Action : Refreshing list or reload
data object OnRefreshAction: AlbumListActions