package com.adevinta.data.remote.services

import com.adevinta.data.models.responses.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApiService {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): Response<List<AlbumResponse>>
}

