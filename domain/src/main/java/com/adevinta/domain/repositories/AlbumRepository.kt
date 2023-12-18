package com.adevinta.domain.repositories

import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumLocalStore
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbumsPaged(): Flow<PagingData<AlbumEntity>>

    suspend fun refreshAlbums(): Result<Unit>
}

internal class AlbumRepositoryImpl(
    private val albumDataStore: AlbumDataStore,
    private val albumLocalStore: AlbumLocalStore
) : AlbumRepository {

    override fun getAlbumsPaged(): Flow<PagingData<AlbumEntity>> {
        return albumLocalStore.getLocalAlbumsPaged()
    }

    // During the refresh we set the datas in ROOM
    override suspend fun refreshAlbums(): Result<Unit> {
        val remoteAlbumsResult = albumDataStore.getAlbums()
        if (remoteAlbumsResult.isSuccess) {
            remoteAlbumsResult.getOrNull()?.let { albumLocalStore.saveLocalAlbums(it) }
            return Result.success(Unit)
        }
        return Result.failure(NoAlbumsException)
    }
}
