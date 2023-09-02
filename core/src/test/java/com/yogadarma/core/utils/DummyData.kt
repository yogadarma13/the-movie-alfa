package com.yogadarma.core.utils

import com.yogadarma.core.data.source.remote.model.MovieListItem
import com.yogadarma.core.data.source.remote.model.MovieListResponse

object DummyData {
    fun generateMoviesListResponse() = MovieListResponse(
        page = 1, totalPages = 123, totalResults = 1234, results = listOf(
            MovieListItem(
                adult = false,
                backdropPath = "/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
                genreIds = listOf(28, 878, 27),
                id = 615656,
                originalLanguage = "en",
                originalTitle = "Meg 2= The Trench",
                overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
                popularity = 6913.755,
                posterPath = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
                releaseDate = "2023-08-02",
                title = "Meg 2= The Trench",
                video = false,
                voteAverage = 7.0,
                voteCount = 1174
            ),
            MovieListItem(
                adult = false,
                backdropPath = "/jZIYaISP3GBSrVOPfrp98AMa8Ng.jpg",
                genreIds = listOf(
                    16,
                    35,
                    10751,
                    14,
                    10749
                ),
                id = 976573,
                originalLanguage = "en",
                originalTitle = "Elemental",
                overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
                popularity = 1782.085,
                posterPath = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
                releaseDate = "2023-06-14",
                title = "Elemental",
                video = false,
                voteAverage = 7.8,
                voteCount = 1648
            )
        )
    )
}