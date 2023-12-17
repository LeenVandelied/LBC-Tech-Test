package com.adevinta.data.mapper

import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.models.database.AlbumRoomEntity
import com.adevinta.data.models.responses.AlbumResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlbumMapperTest {

    @Test
    fun `AlbumResponse is correctly mapped to AlbumEntity`() {
        val albumResponse = AlbumResponse(id = 1, title = "Test", url = "http://test.com", albumId = 11, thumbnailUrl = "http://test.com/thumb")
        val albumEntity = albumResponse.toAlbumEntity()

        assertEquals(albumResponse.id, albumEntity.id)
        assertEquals(albumResponse.title, albumEntity.title)
        assertEquals(albumResponse.url, albumEntity.url)
        assertEquals(albumResponse.thumbnailUrl, albumEntity.thumbnailUrl)
    }

    @Test
    fun `AlbumRoomEntity is correctly mapped to AlbumEntity`() {
        val albumRoomEntity = AlbumRoomEntity(id = 1, title = "Test", url = "http://test.com", thumbnailUrl = "http://test.com/thumb")
        val albumEntity = albumRoomEntity.toAlbumEntity()

        assertEquals(albumRoomEntity.id, albumEntity.id)
        assertEquals(albumRoomEntity.title, albumEntity.title)
        assertEquals(albumRoomEntity.url, albumEntity.url)
        assertEquals(albumRoomEntity.thumbnailUrl, albumEntity.thumbnailUrl)
    }

    @Test
    fun `AlbumEntity is correctly mapped to AlbumRoomEntity`() {
        val albumEntity = AlbumEntity(id = 1, title = "Test", url = "http://test.com", thumbnailUrl = "http://test.com/thumb")
        val albumRoomEntity = albumEntity.toAlbumRoomEntity()

        assertEquals(albumEntity.id, albumRoomEntity.id)
        assertEquals(albumEntity.title, albumRoomEntity.title)
        assertEquals(albumEntity.url, albumRoomEntity.url)
        assertEquals(albumEntity.thumbnailUrl, albumRoomEntity.thumbnailUrl)
    }
}
