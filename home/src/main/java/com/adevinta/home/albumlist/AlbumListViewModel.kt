package com.adevinta.home.albumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adevinta.core.models.AlbumEntity
import com.adevinta.home.albumlist.domain.GetAlbumsPagedUseCase
import com.adevinta.home.albumlist.domain.RefreshAlbumsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val getAlbumsPagedUseCase: GetAlbumsPagedUseCase,
    private val refreshAlbumsUseCase: RefreshAlbumsUseCase
) : ViewModel() {

    private val _albums = MutableStateFlow<PagingData<AlbumEntity>>(PagingData.empty())
    internal val albumsState: StateFlow<PagingData<AlbumEntity>> = _albums.asStateFlow()
    private val _sideEffect = Channel<Throwable>(Channel.BUFFERED)
    internal val sideEffect = _sideEffect.receiveAsFlow()

    // we call refresh to always have fresh data when the app is loaded.
    // Another way would have been to retrieve the data from the database directly and ask the user to force a refresh, this would have allowed better performance when launching the application
    init {
        refresh()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumsPagedUseCase.execute().cachedIn(this).collectLatest { pagingData ->
                _albums.value = pagingData
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            refreshAlbumsUseCase.invoke()
                .onFailure {
                    _sideEffect.send(it)
                }
            getAlbums()
        }
    }
}
