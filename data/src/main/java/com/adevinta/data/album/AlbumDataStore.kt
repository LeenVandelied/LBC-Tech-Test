package com.adevinta.data.album

import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.ResponseException
import com.adevinta.data.mapper.toAlbumEntity
import com.adevinta.data.remote.services.AlbumApiService
import com.adevinta.data.runSafeHttpCall

// DataStore get data from remote resources
interface AlbumDataStore {
    suspend fun getAlbums(): Result<List<AlbumEntity>>
}

internal class AlbumDataStoreImpl(private val albumApiService: AlbumApiService) : AlbumDataStore {
    override suspend fun getAlbums(): Result<List<AlbumEntity>> = runSafeHttpCall {
        val albums = albumApiService.getAlbums()

        return@runSafeHttpCall if (albums.isSuccessful) {
            Result.success(
                albums.body()?.map { albumResponse -> albumResponse.toAlbumEntity() } ?: listOf()
            )
        } else {
            Result.failure(ResponseException(code = albums.code(), message = albums.message()))
        }
    }
}
