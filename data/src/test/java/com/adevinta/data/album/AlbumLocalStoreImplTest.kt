import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.data.album.AlbumLocalStoreImpl
import com.adevinta.data.db.cache.Cache
import com.adevinta.data.db.cache.CacheKeys
import com.adevinta.data.db.persistence.LocalDb
import com.adevinta.data.db.persistence.LocalDbKeys
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class AlbumLocalStoreImplTest {

    private lateinit var cache: Cache
    private lateinit var localDb: LocalDb
    private lateinit var albumLocalStore: AlbumLocalStoreImpl

    @Before
    fun setUp() {
        cache = mock(Cache::class.java)
        localDb = mock(LocalDb::class.java)
        albumLocalStore = AlbumLocalStoreImpl(cache, localDb)
    }

    @Test
    fun `getLocalAlbums returns albums from cache`() = runBlocking {
        val mockAlbums =
            listOf(AlbumEntity(1, "Album 1", "http://example.com/1", "http://example.com/11"))
        Mockito.`when`(cache.read<List<AlbumEntity>>(CacheKeys.ALBUMS)).thenReturn(mockAlbums)

        val result = albumLocalStore.getLocalAlbums()

        assertTrue(result.isSuccess)
        assertEquals(mockAlbums, result.getOrNull())
    }

    @Test
    fun `getLocalAlbums returns albums from localDb when cache is empty`() = runBlocking {
        val mockAlbums =
            listOf(AlbumEntity(2, "Album 2", "http://example.com/2", "http://example.com/22"))
        Mockito.`when`(cache.read<List<AlbumEntity>>(CacheKeys.ALBUMS)).thenReturn(null)
        Mockito.`when`(localDb.read<List<AlbumEntity>>(LocalDbKeys.ALBUMS)).thenReturn(mockAlbums)

        val result = albumLocalStore.getLocalAlbums()

        assertTrue(result.isSuccess)
        assertEquals(mockAlbums, result.getOrNull())
    }

    @Test
    fun `getLocalAlbums returns failure when both cache and localDb are empty`() = runBlocking {
        Mockito.`when`(cache.read<List<AlbumEntity>>(CacheKeys.ALBUMS)).thenReturn(null)
        Mockito.`when`(localDb.read<List<AlbumEntity>>(LocalDbKeys.ALBUMS)).thenReturn(null)

        val result = albumLocalStore.getLocalAlbums()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is NoAlbumsException)
    }

    @Test
    fun `saveLocalAlbums saves albums to both cache and localDb`() = runBlocking {
        val mockAlbums =
            listOf(AlbumEntity(3, "Album 3", "http://example.com/3", "http://example.com/33"))

        albumLocalStore.saveLocalAlbums(mockAlbums)

        verify(cache).save(CacheKeys.ALBUMS, mockAlbums)
        verify(localDb).save(LocalDbKeys.ALBUMS, mockAlbums)
    }
}
