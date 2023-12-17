package com.adevinta.domain.repositories

import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumLocalStore
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbumsPaged(forceRefresh: Boolean = false): Flow<PagingData<AlbumEntity>>

    suspend fun refreshAlbums()
}

internal class AlbumRepositoryImpl(
    private val albumDataStore: AlbumDataStore,
    private val albumLocalStore: AlbumLocalStore
) : AlbumRepository {

    override fun getAlbumsPaged(forceRefresh: Boolean): Flow<PagingData<AlbumEntity>> {
        return albumLocalStore.getLocalAlbumsPaged()
    }

    override suspend fun refreshAlbums() {
        val remoteAlbumsResult = albumDataStore.getAlbums()
        if (remoteAlbumsResult.isSuccess) {
            remoteAlbumsResult.getOrNull()?.let { albumLocalStore.saveLocalAlbums(it) }
        }
    }
}
