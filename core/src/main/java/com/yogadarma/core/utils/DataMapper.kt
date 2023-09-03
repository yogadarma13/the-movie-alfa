package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListItem
import com.yogadarma.core.domain.model.Movie


fun MovieListItem.toMovieEntity() = MovieEntity(
    id = id.toString(),
    poster = posterPath,
    title = title,
    overview = overview,
    voteAverage = voteAverage
)

fun MovieDetailResponse.toMovieEntity() = MovieEntity(
    id = id.toString(),
    poster = posterPath,
    title = title,
    overview = overview,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genres = genres?.map { it.name }?.joinToString(", ")
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    poster = poster,
    title = title,
    overview = overview,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genres = genres
)
