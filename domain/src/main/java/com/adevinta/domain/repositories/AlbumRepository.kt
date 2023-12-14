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

    // Force refresh will not work correctly if data don't change ( it's the case for this test )
    override suspend fun getAlbums(forceRefresh: Boolean): Result<List<AlbumEntity>> {
        // if forceRefresh is false we get data from cache or LocalDB
        if (!forceRefresh) {
            val localAlbumsResult = albumLocalStore.getLocalAlbums()
            if (localAlbumsResult.isSuccess) {
                return localAlbumsResult
            }
        }

        // else we get data from remote
        val remoteAlbumsResult = albumDataStore.getAlbums()
        if (remoteAlbumsResult.isSuccess) {
            remoteAlbumsResult.getOrNull()?.let { albumLocalStore.saveLocalAlbums(it) }
            return remoteAlbumsResult
        }

        return Result.failure(NoAlbumsException)
    }
}
