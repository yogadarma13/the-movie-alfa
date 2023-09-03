package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.data.source.remote.model.VideoResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getMoviesData(page: Int): Flow<ApiResponse<MovieListResponse>>

    fun getMovieDetail(movieId: String): Flow<ApiResponse<MovieDetailResponse>>

    fun getMovieReviews(movieId: String): Flow<ApiResponse<ReviewResponse>>

    fun getMovieVideos(movieId: String): Flow<ApiResponse<VideoResponse>>

}
