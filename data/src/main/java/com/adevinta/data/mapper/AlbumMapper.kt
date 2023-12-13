package com.adevinta.data.mapper

import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.models.responses.AlbumResponse

internal fun AlbumResponse.toAlbumEntity() =
    with(this) {
        AlbumEntity(
            id = id,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl
        )
    }
