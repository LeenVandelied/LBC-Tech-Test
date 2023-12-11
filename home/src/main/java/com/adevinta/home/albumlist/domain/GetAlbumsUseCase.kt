package com.adevinta.home.albumlist.domain

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.domain.UseCase
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class GetAlbumsUseCase(
    dispatcherIo: CoroutineDispatcher,
    private val albumRepository: AlbumRepository
) : UseCase<Result<List<AlbumEntity>>>(dispatcherIo) {
    override suspend fun execute(): Result<List<AlbumEntity>> {
        val albumsResult = albumRepository.getAlbums()

        if (albumsResult.isFailure) return Result.failure(NoAlbumsException)

        return Result.success(requireNotNull(albumsResult.getOrNull()))
    }
}
