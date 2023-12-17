import com.adevinta.data.mapper.toAlbumEntity
import com.adevinta.data.models.responses.AlbumResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlbumMapperTest {

    @Test
    fun `map AlbumResponse to AlbumEntity`() {
        val albumResponse =
            AlbumResponse(
                albumId = 1,
                id = 123,
                title = "Test Album",
                url = "http://example.com/album",
                thumbnailUrl = "http://example.com/thumbnail"
            )

        val albumEntity = albumResponse.toAlbumEntity()

        // Verifying that each property is mapped correctly
        assertEquals(albumResponse.id, albumEntity.id)
        assertEquals(albumResponse.title, albumEntity.title)
        assertEquals(albumResponse.url, albumEntity.url)
        assertEquals(albumResponse.thumbnailUrl, albumEntity.thumbnailUrl)
    }
}
