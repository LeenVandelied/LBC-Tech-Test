import com.adevinta.domain.repositories.AlbumRepository
import com.adevinta.home.albumlist.domain.RefreshAlbumsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class RefreshAlbumsUseCaseTest {

    private lateinit var albumRepository: AlbumRepository
    private lateinit var refreshAlbumsUseCase: RefreshAlbumsUseCase
    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        albumRepository = mock()
        refreshAlbumsUseCase = RefreshAlbumsUseCase(testDispatcher, albumRepository)
    }

    @Test
    fun `execute calls refreshAlbums on albumRepository`() =
        runTest(testDispatcher) {
            refreshAlbumsUseCase.execute(true)

            verify(albumRepository).refreshAlbums()
        }
}
