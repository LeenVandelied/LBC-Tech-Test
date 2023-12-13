package com.adevinta.home.albumlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.core.models.AlbumEntity
import com.adevinta.domain.ResultState
import com.adevinta.home.albumlist.domain.GetAlbumsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AlbumListViewModel(private val getAlbumsUseCase: GetAlbumsUseCase) : ViewModel() {

    private val _albums =
        MutableStateFlow<ResultState<List<AlbumEntity>>>(ResultState.Uninitialized)
    internal val albumsState = _albums.asStateFlow()
    private val _sideEffect = Channel<Throwable>(Channel.BUFFERED)
    internal val sideEffect = _sideEffect.receiveAsFlow()
    private var _isRefreshing by mutableStateOf(false)
    internal val isRefreshing = _isRefreshing

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase
                .invoke(false)
                .onSuccess { albums ->
                    _albums.update { ResultState.Success(albums) }
                }
                .onFailure { error ->
                    _albums.update { ResultState.Failure(error) }
                    _sideEffect.send(error)
                }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing = true
            getAlbumsUseCase
                .invoke(true)
                .onSuccess { albums ->
                    _albums.update { ResultState.Success(albums) }
                    _isRefreshing = false
                }
                .onFailure { error ->
                    _albums.update { ResultState.Failure(error) }
                    _sideEffect.send(error)
                    _isRefreshing = false
                }
        }
    }
}
