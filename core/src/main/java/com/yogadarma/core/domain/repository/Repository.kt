package com.yogadarma.core.domain.repository

import androidx.paging.PagingData
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.model.Review
import com.yogadarma.core.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getMoviesData(): Flow<PagingData<Movie>>

    fun getMovieDetail(movieId: String): Flow<Resource<Movie>>

    fun getReviewsData(movieId: String): Flow<Resource<Review>>

    fun getVideoData(movieId: String): Flow<Resource<Video>>
}