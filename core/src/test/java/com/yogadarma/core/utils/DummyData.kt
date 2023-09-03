package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListItem
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.domain.model.Movie

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

    fun generateMoviesListEntity() = listOf(
        MovieEntity(
            id = "615656",
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            title = "Meg 2= The Trench",
            voteAverage = 7.0
        ),
        MovieEntity(
            id = "976573",
            overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
            poster = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
            title = "Elemental",
            voteAverage = 7.8,
        )
    )

    fun generateRemoteKeysEntityList() = listOf(
        RemoteKeysEntity(
            id = "key1",
            prevKey = 1,
            nextKey = 2
        ),
        RemoteKeysEntity(
            id = "key2",
            prevKey = 2,
            nextKey = 3
        )
    )

    fun generateMoviesList() = listOf(
        Movie(
            id = "615656",
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            title = "Meg 2= The Trench",
            voteAverage = 7.0
        ),
        Movie(
            id = "976573",
            overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
            poster = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
            title = "Elemental",
            voteAverage = 7.8,
        )
    )

    fun generateMovieDetailResponse() = MovieDetailResponse(
        adult = false,
        backdropPath = "/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
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
    )

    fun generateMovieEntity() = MovieEntity(
        id = "615656",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        releaseDate = "2023-08-02",
        title = "Meg 2= The Trench",
        voteAverage = 7.0,
        genres = "Action, Drama"
    )
}