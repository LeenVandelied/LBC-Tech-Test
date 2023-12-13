import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.ResponseException
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumDataStoreImpl
import com.adevinta.data.models.responses.AlbumResponse
import com.adevinta.data.remote.services.AlbumApiService
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

class AlbumDataStoreImplTest {

    private lateinit var albumApiService: AlbumApiService
    private lateinit var albumDataStore: AlbumDataStore

    @Before
    fun setUp() {
        albumApiService = mock(AlbumApiService::class.java)
        albumDataStore = AlbumDataStoreImpl(albumApiService)
    }

    @Test
    fun `getAlbums returns success when API call is successful`() = runBlocking {
        val mockResponse =
            listOf(AlbumResponse(1, 100, "url1", "http://example.com/1", "http://example.com/1"))
        Mockito.`when`(albumApiService.getAlbums()).thenReturn(Response.success(mockResponse))

        val result = albumDataStore.getAlbums()

        assert(result.isSuccess)
        val expected = mockResponse.map { AlbumEntity(it.id, it.title, it.url, it.thumbnailUrl) }
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `getAlbums returns failure when API call is unsuccessful`() = runBlocking {
        val errorCode = 500
        val errorMessage = "Response exception code 500 - Response.error()"
        Mockito.`when`(albumApiService.getAlbums())
            .thenReturn(Response.error(errorCode, "".toResponseBody(null)))

        val result = albumDataStore.getAlbums()

        assert(result.isFailure)
        assert(result.exceptionOrNull() is ResponseException)
        assertEquals(errorMessage, (result.exceptionOrNull() as ResponseException).message)
    }
}
