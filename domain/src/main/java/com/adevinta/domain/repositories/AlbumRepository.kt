package com.adevinta.domain.repositories

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumLocalStore

interface AlbumRepository {
    suspend fun getAlbums(forceRefresh: Boolean = false): Result<List<AlbumEntity>>
}

internal class AlbumRepositoryImpl(
    private val albumDataStore: AlbumDataStore,
    private val albumLocalStore: AlbumLocalStore
) : AlbumRepository {
    override suspend fun getAlbums(forceRefresh: Boolean): Result<List<AlbumEntity>> {
        val albums = if (forceRefresh) null else albumLocalStore.getLocalAlbums()

        if (albums?.isSuccess == true) {
            return Result.success(requireNotNull(albums.getOrNull()))
        }

        val remoteAlbums = albumDataStore.getAlbums()
        if (remoteAlbums.isSuccess) {
            albumLocalStore.saveLocalAlbums(remoteAlbums.getOrThrow())
            return remoteAlbums
        }
        return Result.failure(NoAlbumsException)
    }
}