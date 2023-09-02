package com.yogadarma.core.domain.model

data class MovieItem(
    val id: String,
    val poster: String,
    val title: String,
    val overview: String,
    val voteAverage: Double
)