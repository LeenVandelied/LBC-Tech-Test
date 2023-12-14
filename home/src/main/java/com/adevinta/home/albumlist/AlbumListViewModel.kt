package com.adevinta.home.albumlist

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

    init {
        getAlbums(false)
    }

    internal fun getAlbums(forceRefresh: Boolean) {
        viewModelScope.launch {
            getAlbumsUseCase
                .invoke(forceRefresh)
                .onSuccess { albums ->
                    _albums.update { ResultState.Success(albums) }
                }
                .onFailure { error ->
                    _albums.update { ResultState.Failure(error) }
                    _sideEffect.send(error)
                }
        }
    }

}
