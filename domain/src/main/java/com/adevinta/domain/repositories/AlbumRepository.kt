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

    /**
     * Managed case :
     * 1. First load : ForceRefresh is false, get local data ( empty ) and return remote data + save it
     * 2. First load without connection : return NoAlbumsException
     * 3. Next loads : return local datas
     * 4. Next loads without connection : return local datas
     * 5. ForceRefresh : return remote datas
     * 6. ForceRefresh without connection : try to get remote data, if fail, return local data, if no data, return NoAlbumsException
     *
     * In this test case data are static, that's why I don't forceRefresh the data when the app start
     * In an other case we can get data, compare with local and update if necessary
     *
     */
    override suspend fun getAlbums(forceRefresh: Boolean): Result<List<AlbumEntity>> {
        if (!forceRefresh) {
            val localAlbumsResult = albumLocalStore.getLocalAlbums()
            if (localAlbumsResult.isSuccess) {
                return localAlbumsResult
            }
        }

        val remoteAlbumsResult = albumDataStore.getAlbums()
        if (remoteAlbumsResult.isSuccess) {
            remoteAlbumsResult.getOrNull()?.let { albumLocalStore.saveLocalAlbums(it) }
            return remoteAlbumsResult
        } else if (forceRefresh) {
            val fallbacklocalAlbumsResult = albumLocalStore.getLocalAlbums()
            if (fallbacklocalAlbumsResult.isSuccess) {
                return fallbacklocalAlbumsResult
            }
        }

        return Result.failure(NoAlbumsException)
    }
}
