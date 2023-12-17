import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.album.AlbumDataStore
import com.adevinta.data.album.AlbumLocalStore
import com.adevinta.domain.repositories.AlbumRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AlbumRepositoryImplTest {

    private lateinit var albumDataStore: AlbumDataStore
    private lateinit var albumLocalStore: AlbumLocalStore
    private lateinit var albumRepositoryImpl: AlbumRepositoryImpl

    @BeforeEach
    fun setup() {
        albumDataStore = mock()
        albumLocalStore = mock()
        albumRepositoryImpl = AlbumRepositoryImpl(albumDataStore, albumLocalStore)
    }

    @Test
    fun `getAlbumsPaged calls getLocalAlbumsPaged from albumLocalStore`() = runTest {
        val fakeFlow: Flow<PagingData<AlbumEntity>> = flowOf(PagingData.empty())
        whenever(albumLocalStore.getLocalAlbumsPaged()).thenReturn(fakeFlow)

        albumRepositoryImpl.getAlbumsPaged(false)

        verify(albumLocalStore).getLocalAlbumsPaged()
    }

    @Test
    fun `refreshAlbums calls getAlbums from albumDataStore and saveLocalAlbums from albumLocalStore on success`() =
        runTest {
            val fakeAlbums = listOf(AlbumEntity(1, "Test Album", "url", "thumbnail"))
            whenever(albumDataStore.getAlbums()).thenReturn(Result.success(fakeAlbums))

            albumRepositoryImpl.refreshAlbums()

            verify(albumDataStore).getAlbums()
            verify(albumLocalStore).saveLocalAlbums(fakeAlbums)
        }
}
