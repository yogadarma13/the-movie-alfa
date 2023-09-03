package com.yogadarma.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val list: List<ReviewItem>? = null
): Parcelable

@Parcelize
data class ReviewItem(
    val reviewId: String? = null,
    val username: String? = null,
    val date: String? = null,
    val avatar: String? = null,
    val content: String? = null,
): Parcelable