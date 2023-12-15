package com.adevinta.data.db.persistence

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.adevinta.data.models.database.AlbumRoomEntity

@Dao
interface LocalDbDao {
    @Query("SELECT * FROM albums")
    suspend fun getAll(): List<AlbumRoomEntity>

    @Upsert
    suspend fun insertAll(albums: List<AlbumRoomEntity>)

}
