package com.adevinta.home.albumlist.domain

import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

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
    fun `refreshAlbums should call refreshAlbums on repository`() = runTest(testDispatcher) {
        whenever(albumRepository.refreshAlbums()).thenReturn(Result.success(Unit))

        refreshAlbumsUseCase.execute()

        verify(albumRepository).refreshAlbums()
    }

    @Test
    fun `refreshAlbums should handle failure`() = runTest(testDispatcher) {
        whenever(albumRepository.refreshAlbums()).thenReturn(Result.failure(com.adevinta.core.models.NoAlbumsException))

        val result = refreshAlbumsUseCase.execute()

        assert(result.isFailure)
    }
}
