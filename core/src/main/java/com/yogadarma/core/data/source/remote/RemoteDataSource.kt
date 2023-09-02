package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getMoviesData(page: Int): Flow<ApiResponse<MovieListResponse>>

    fun getMovieDetail(movieId: String): Flow<ApiResponse<MovieDetailResponse>>
}
