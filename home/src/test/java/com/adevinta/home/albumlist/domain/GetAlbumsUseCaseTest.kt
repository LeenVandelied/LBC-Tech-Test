package com.adevinta.home.albumlist.domain

import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetAlbumsPagedUseCaseTest {

    private lateinit var albumRepository: AlbumRepository
    private lateinit var getAlbumsPagedUseCase: GetAlbumsPagedUseCase

    @BeforeEach
    fun setUp() {
        albumRepository = mock()
        getAlbumsPagedUseCase = GetAlbumsPagedUseCase(albumRepository)
    }

    @Test
    fun `execute calls albumRepository getAlbumsPaged with correct params`() = runTest {
        val fakePagingData: Flow<PagingData<AlbumEntity>> = flowOf(PagingData.empty())
        whenever(albumRepository.getAlbumsPaged()).thenReturn(fakePagingData)

        val result = getAlbumsPagedUseCase.execute()

        assertNotNull(result)
        assertEquals(fakePagingData, result)
    }
}
