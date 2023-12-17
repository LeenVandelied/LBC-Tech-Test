package com.adevinta.home.albumlist.domain

import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow

internal class GetAlbumsPagedUseCase(private val albumRepository: AlbumRepository) {
     fun execute(): Flow<PagingData<AlbumEntity>> =
        albumRepository.getAlbumsPaged()
}
