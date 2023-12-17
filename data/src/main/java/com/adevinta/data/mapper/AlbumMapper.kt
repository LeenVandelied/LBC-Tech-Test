package com.adevinta.data.mapper

import com.adevinta.core.models.AlbumEntity
import com.adevinta.data.models.database.AlbumRoomEntity
import com.adevinta.data.models.responses.AlbumResponse

// convert remote data into core data, remove useless datas
internal fun AlbumResponse.toAlbumEntity() =
    with(this) { AlbumEntity(id = id, title = title, url = url, thumbnailUrl = thumbnailUrl) }

internal fun AlbumRoomEntity.toAlbumEntity() =
    with(this) { AlbumEntity(id = id, title = title, url = url, thumbnailUrl = thumbnailUrl) }

internal fun AlbumEntity.toAlbumRoomEntity() =
    with(this) { AlbumRoomEntity(id = id, title = title, url = url, thumbnailUrl = thumbnailUrl) }
