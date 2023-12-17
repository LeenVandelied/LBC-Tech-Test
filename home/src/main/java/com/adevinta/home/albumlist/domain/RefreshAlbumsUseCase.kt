package com.adevinta.home.albumlist.domain

import com.adevinta.domain.UseCaseWithParams
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class RefreshAlbumsUseCase(
    dispatcherIo: CoroutineDispatcher,
    private val albumRepository: AlbumRepository
) : UseCaseWithParams<Boolean, Unit>(dispatcherIo) {
    override suspend fun execute(params: Boolean) {
        return albumRepository.refreshAlbums()
    }
}
