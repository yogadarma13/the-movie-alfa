package com.yogadarma.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yogadarma.core.data.source.local.room.converter.EntityConverter
import com.yogadarma.core.data.source.local.room.dao.MovieDao
import com.yogadarma.core.data.source.local.room.dao.RemoteKeysDao
import com.yogadarma.core.data.source.local.room.dao.ReviewDao
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity

@TypeConverters(value = [EntityConverter::class])
@Database(
    entities = [MovieEntity::class, RemoteKeysEntity::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun reviewDao(): ReviewDao
}
