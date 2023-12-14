package com.adevinta.data.album

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.db.cache.Cache
import com.adevinta.data.db.cache.CacheKeys
import com.adevinta.data.db.persistence.LocalDb
import com.adevinta.data.db.persistence.LocalDbKeys

interface AlbumLocalStore {
    suspend fun getLocalAlbums(): Result<List<AlbumEntity>>

    suspend fun saveLocalAlbums(albums: List<AlbumEntity>)
}

internal class AlbumLocalStoreImpl(private val cache: Cache, private val localDb: LocalDb) :
    AlbumLocalStore {
    override suspend fun getLocalAlbums(): Result<List<AlbumEntity>> {
        // get temporary cached albums during navigation in the app between different screen
        val cacheAlbums = cache.read<List<AlbumEntity>>(key = CacheKeys.ALBUMS)
        if (cacheAlbums != null) {
            return Result.success(cacheAlbums)
        }

        // get local db saved albums if the app was not cleared for data
        val dbAlbums = localDb.read<List<AlbumEntity>>(key = LocalDbKeys.ALBUMS)
        if (dbAlbums != null) {
            return Result.success(dbAlbums)
        }

        // return exception if no data
        return Result.failure(NoAlbumsException)
    }

    override suspend fun saveLocalAlbums(albums: List<AlbumEntity>) {
        localDb.save(key = LocalDbKeys.ALBUMS, data = albums)
        cache.save(key = CacheKeys.ALBUMS, data = albums)
    }
}
