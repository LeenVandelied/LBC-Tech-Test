package com.adevinta.data.album

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.db.cache.Cache
import com.adevinta.data.db.cache.CacheKeys
import com.adevinta.data.db.persistence.LocalDbDao
import com.adevinta.data.mapper.toAlbumEntity
import com.adevinta.data.mapper.toAlbumRoomEntity

interface AlbumLocalStore {
    suspend fun getLocalAlbums(): Result<List<AlbumEntity>>

    suspend fun saveLocalAlbums(albums: List<AlbumEntity>)
}

internal class AlbumLocalStoreImpl(private val cache: Cache, private val localDbDao: LocalDbDao) :
    AlbumLocalStore {
    override suspend fun getLocalAlbums(): Result<List<AlbumEntity>> {

        // get temporary cached albums during navigation in the app between different screen
        val cacheAlbums = cache.read<List<AlbumEntity>>(key = CacheKeys.ALBUMS)
        if (cacheAlbums != null) {
            return Result.success(cacheAlbums)
        }

        val dbAlbumsDao = localDbDao.getAll()
        if (dbAlbumsDao.isNotEmpty()) {
            return Result.success(dbAlbumsDao.map { it.toAlbumEntity() })
        }

        // return exception if no data
        return Result.failure(NoAlbumsException)
    }

    override suspend fun saveLocalAlbums(albums: List<AlbumEntity>) {
        localDbDao.insertAll(albums.map { it.toAlbumRoomEntity() })
        cache.save(key = CacheKeys.ALBUMS, data = albums)
    }
}
