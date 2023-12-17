package com.adevinta.home.albumlist.domain

import com.adevinta.domain.UseCase
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class RefreshAlbumsUseCase(
    dispatcherIo: CoroutineDispatcher,
    private val albumRepository: AlbumRepository
) : UseCase<Result<Unit>>(dispatcherIo) {
    override suspend fun execute(): Result<Unit> {
        return albumRepository.refreshAlbums()
    }
}
