package com.yogadarma.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogadarma.core.data.source.local.room.entity.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoEntity: VideoEntity)

    @Query("SELECT * FROM video WHERE id=:id")
    suspend fun getVideo(id: String): VideoEntity
}