package com.yogadarma.core.data.source.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yogadarma.core.data.source.local.room.entity.ReviewColumn

class EntityConverter {

    @TypeConverter
    fun fromReviewToJson(reviewColumn: ReviewColumn): String = Gson().toJson(reviewColumn)

    @TypeConverter
    fun fromJsonToReview(json: String): ReviewColumn =
        Gson().fromJson(json, ReviewColumn::class.java)
}