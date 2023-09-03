package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
    override fun getMoviesData(page: Int): Flow<ApiResponse<MovieListResponse>> = flow {
        try {
            val response = apiService.getMoviesData(page)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieDetail(movieId: String): Flow<ApiResponse<MovieDetailResponse>> = flow {
        try {
            val response = apiService.getMovieDetail(movieId)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieReviews(movieId: String): Flow<ApiResponse<ReviewResponse>> = flow {
        try {
            val response = apiService.getMovieReviews(movieId)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
