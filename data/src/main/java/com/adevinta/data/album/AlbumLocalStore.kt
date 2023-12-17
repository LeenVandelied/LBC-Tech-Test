package com.adevinta.data.album

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.db.persistence.LocalDbDao
import com.adevinta.data.mapper.toAlbumEntity
import com.adevinta.data.mapper.toAlbumRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface AlbumLocalStore {
    fun getLocalAlbumsPaged(): Flow<PagingData<AlbumEntity>>

    fun saveLocalAlbums(albums: List<AlbumEntity>)
}

internal class AlbumLocalStoreImpl(private val localDbDao: LocalDbDao) : AlbumLocalStore {
    override fun getLocalAlbumsPaged(): Flow<PagingData<AlbumEntity>> =
        Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = true, maxSize = 200),
                pagingSourceFactory = { localDbDao.getAllPaged() }
            )
            .flow
            .map { pagingData ->
                pagingData.map { albumRoomEntity -> albumRoomEntity.toAlbumEntity() }
            }

    override fun saveLocalAlbums(albums: List<AlbumEntity>) {
        localDbDao.insertAll(albums.map { it.toAlbumRoomEntity() })
    }
}
