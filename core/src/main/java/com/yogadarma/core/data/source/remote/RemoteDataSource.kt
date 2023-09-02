package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.model.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getMoviesData(page: Int): Flow<ApiResponse<MovieListResponse>>
}
