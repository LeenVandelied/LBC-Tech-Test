package com.adevinta.home.albumlist.domain

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.domain.UseCaseWithParams
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class GetAlbumsUseCase(
    dispatcherIo: CoroutineDispatcher,
    private val albumRepository: AlbumRepository
) : UseCaseWithParams<Boolean, Result<List<AlbumEntity>>>(dispatcherIo) {
    override suspend fun execute(params: Boolean): Result<List<AlbumEntity>> {
        val albumsResult = albumRepository.getAlbums(forceRefresh = params)

        if (albumsResult.isFailure) return Result.failure(NoAlbumsException)

        return Result.success(requireNotNull(albumsResult.getOrNull()))
    }
}
