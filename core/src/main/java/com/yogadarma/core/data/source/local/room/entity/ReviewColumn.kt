package com.yogadarma.core.data.source.local.room.entity

data class ReviewColumn(
    val reviewList: List<ReviewItemColumn>? = null
)

data class ReviewItemColumn(
    val reviewId: String? = null,
    val username: String? = null,
    val date: String? = null,
    val avatar: String? = null,
    val content: String? = null,
)

