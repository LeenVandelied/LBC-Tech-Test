package com.adevinta.data.db.persistence

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.adevinta.data.models.database.AlbumRoomEntity

@Dao
interface LocalDbDao {
    @Query("SELECT * FROM albums") fun getAllPaged(): PagingSource<Int, AlbumRoomEntity>

    @Upsert fun insertAll(albums: List<AlbumRoomEntity>)
}
