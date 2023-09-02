package com.yogadarma.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogadarma.core.data.source.local.room.dao.MovieDao
import com.yogadarma.core.data.source.local.room.dao.RemoteKeysDao
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity

@Database(
    entities = [MovieEntity::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
