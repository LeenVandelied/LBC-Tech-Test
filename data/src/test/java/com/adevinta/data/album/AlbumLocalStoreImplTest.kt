package com.adevinta.data.album

import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.db.persistence.LocalDbDao
import com.adevinta.data.mapper.toAlbumRoomEntity
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AlbumLocalStoreImplTest {

    private val mockLocalDbDao: LocalDbDao = mock()
    private val albumLocalStore = AlbumLocalStoreImpl(mockLocalDbDao)

    @Test
    fun `saveLocalAlbums saves albums to DB and cache`() = runBlocking {
        val albums = listOf(AlbumEntity(1, "Album 1", "url", "thumbnail"))

        albumLocalStore.saveLocalAlbums(albums)

        verify(mockLocalDbDao).insertAll(albums.map { it.toAlbumRoomEntity() })
    }
}
