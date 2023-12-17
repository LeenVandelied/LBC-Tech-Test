package com.adevinta.home.albumlist.domain

import androidx.paging.PagingData
import com.adevinta.core.models.AlbumEntity
import com.adevinta.domain.FlowUseCaseWithParams
import com.adevinta.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow

internal class GetAlbumsPagedUseCase(private val albumRepository: AlbumRepository) :
    FlowUseCaseWithParams<Boolean, Flow<PagingData<AlbumEntity>>>() {
    override fun execute(params: Boolean): Flow<PagingData<AlbumEntity>> =
        albumRepository.getAlbumsPaged(forceRefresh = params)
}
