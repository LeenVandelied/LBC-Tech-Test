package com.adevinta.data.album

import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.models.responses.AlbumResponse
import com.adevinta.data.remote.services.AlbumApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class AlbumDataStoreImplTest {

    private lateinit var albumApiService: AlbumApiService
    private lateinit var albumDataStore: AlbumDataStoreImpl

    @BeforeEach
    fun setup() {
        albumApiService = mock()
        albumDataStore = AlbumDataStoreImpl(albumApiService)
    }

    @Test
    fun `getAlbums returns success when API call is successful`() = runTest {
        val mockResponse =
            listOf(
                AlbumResponse(
                    1,
                    123,
                    "Test Album",
                    "http://example.com/album",
                    "http://example.com/thumbnail"
                )
            )
        whenever(albumApiService.getAlbums()).thenReturn(Response.success(mockResponse))

        val result = albumDataStore.getAlbums()

        assertTrue(result.isSuccess)
        val expected = mockResponse.map { AlbumEntity(it.id, it.title, it.url, it.thumbnailUrl) }
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `getAlbums returns failure when API call is unsuccessful`() = runTest {
        val errorCode = 500
        whenever(albumApiService.getAlbums())
            .thenReturn(Response.error(errorCode, "".toResponseBody()))

        val result = albumDataStore.getAlbums()

        assertTrue(result.isFailure)
    }
}
