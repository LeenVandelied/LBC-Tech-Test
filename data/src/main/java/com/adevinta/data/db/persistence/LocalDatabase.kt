package com.adevinta.data.db.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adevinta.data.models.database.AlbumRoomEntity

@Database(entities = [AlbumRoomEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDbDao(): LocalDbDao
}
