package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewColumn
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewItemColumn
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListItem
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.model.Review
import com.yogadarma.core.domain.model.ReviewItem


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

fun ReviewEntity.toReview() = Review(
    list = review?.reviewList?.map {
        ReviewItem(
            reviewId = it.reviewId,
            username = it.username,
            date = it.date,
            avatar = it.avatar,
            content = it.content
        )
    }
)

fun ReviewResponse.toReviewEntity(movieId: String) = ReviewEntity(
    id = movieId,
    review = ReviewColumn(
        reviewList = results?.map {
            ReviewItemColumn(
                reviewId = it.id,
                username = it.authorDetails?.username,
                date = it.updatedAt,
                avatar = it.authorDetails?.avatarPath,
                content = it.content
            )
        }
    )
)
