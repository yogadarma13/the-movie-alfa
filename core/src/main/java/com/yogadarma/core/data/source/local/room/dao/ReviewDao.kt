package com.yogadarma.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(review: ReviewEntity)

    @Query("SELECT * FROM review WHERE id=:id")
    suspend fun getReviews(id: String): ReviewEntity?
}