import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumLocalStore
import com.adevinta.domain.repositories.AlbumRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class AlbumRepositoryImplTest {

    private lateinit var albumDataStore: AlbumDataStore
    private lateinit var albumLocalStore: AlbumLocalStore
    private lateinit var albumRepository: AlbumRepositoryImpl

    @Before
    fun setUp() {
        albumDataStore = mock(AlbumDataStore::class.java)
        albumLocalStore = mock(AlbumLocalStore::class.java)
        albumRepository = AlbumRepositoryImpl(albumDataStore, albumLocalStore)
    }

    @Test
    fun `getAlbums returns albums from local store when not forcing refresh and local data is available`() =
        runBlocking {
            val localAlbums =
                Result.success(
                    listOf(
                        AlbumEntity(
                            1,
                            "Local Album",
                            "http://example.com/1",
                            "http://example.com/11"
                        )
                    )
                )
            Mockito.`when`(albumLocalStore.getLocalAlbums()).thenReturn(localAlbums)

            val result = albumRepository.getAlbums(forceRefresh = false)

            assertEquals(localAlbums, result)
        }

    @Test
    fun `getAlbums fetches from remote when forcing refresh`() = runBlocking {
        val remoteAlbums =
            Result.success(
                listOf(
                    AlbumEntity(1, "Remote Album", "http://example.com/2", "http://example.com/22")
                )
            )
        Mockito.`when`(albumDataStore.getAlbums()).thenReturn(remoteAlbums)

        val result = albumRepository.getAlbums(forceRefresh = true)

        assertEquals(remoteAlbums, result)
    }

    @Test
    fun `getAlbums fetches from remote when local store is empty`() = runBlocking {
        Mockito.`when`(albumLocalStore.getLocalAlbums())
            .thenReturn(Result.failure(NoAlbumsException))
        val remoteAlbums =
            Result.success(
                listOf(
                    AlbumEntity(1, "Remote Album", "http://example.com/3", "http://example.com/3")
                )
            )
        Mockito.`when`(albumDataStore.getAlbums()).thenReturn(remoteAlbums)

        val result = albumRepository.getAlbums(forceRefresh = false)

        assertEquals(remoteAlbums, result)
    }

    @Test
    fun `getAlbums returns failure when no albums are available`() = runBlocking {
        Mockito.`when`(albumLocalStore.getLocalAlbums())
            .thenReturn(Result.failure(NoAlbumsException))
        Mockito.`when`(albumDataStore.getAlbums()).thenReturn(Result.failure(NoAlbumsException))

        val result = albumRepository.getAlbums(forceRefresh = false)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is NoAlbumsException)
    }
}
