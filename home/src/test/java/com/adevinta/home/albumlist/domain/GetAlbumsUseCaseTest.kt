import com.adevinta.core.models.AlbumEntity
import com.adevinta.domain.repositories.AlbumRepository
import com.adevinta.home.albumlist.domain.GetAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetAlbumsUseCaseTest {

    private lateinit var albumRepository: AlbumRepository
    private lateinit var getAlbumsUseCase: GetAlbumsUseCase

    @Before
    fun setUp() {
        albumRepository = mock(AlbumRepository::class.java)
        getAlbumsUseCase = GetAlbumsUseCase(Dispatchers.Unconfined, albumRepository)
    }

    @Test
    fun `execute returns success when repository returns success`() = runTest {
        val mockAlbums =
            listOf(AlbumEntity(1, "Album 1", "http://example.com/1", "http://example.com/11"))
        Mockito.`when`(albumRepository.getAlbums(true)).thenReturn(Result.success(mockAlbums))

        val result = getAlbumsUseCase.execute(true)

        assertTrue(result.isSuccess)
        assertEquals(mockAlbums, result.getOrNull())
    }

    @Test
    fun `execute returns failure when repository returns failure`() = runTest {
        Mockito.`when`(albumRepository.getAlbums(true)).thenReturn(Result.failure(Exception()))

        val result = getAlbumsUseCase.execute(true)

        assertTrue(result.isFailure)
    }
}
