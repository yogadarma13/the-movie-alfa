package com.yogadarma.core.domain.model

data class Review(
    val list: List<ReviewItem>? = null
)

data class ReviewItem(
    val reviewId: String? = null,
    val username: String? = null,
    val date: String? = null,
    val avatar: String? = null,
    val content: String? = null,
)