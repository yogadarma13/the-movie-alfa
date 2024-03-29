package com.yogadarma.movie_detail.utils

import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.model.Review
import com.yogadarma.core.domain.model.ReviewItem

object DummyData {
    fun generateMovieData() = Movie(
        id = "1234",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        releaseDate = "2023-08-02",
        title = "Meg 2= The Trench",
        voteAverage = 7.0,
        genres = "Action, Drama"
    )

    fun generateReviewsData() = Review(
        list = listOf(
            ReviewItem(
                "1234",
                "tes",
                "2023-12-23",
                "tes.jpg",
                "Content Review"
            )
        )
    )
}