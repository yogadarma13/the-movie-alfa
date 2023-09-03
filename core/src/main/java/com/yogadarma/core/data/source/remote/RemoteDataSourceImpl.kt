package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.data.source.remote.model.VideoResponse
import com.yogadarma.core.data.source.remote.network.ApiService
import com.yogadarma.core.utils.EspressoIdlingResource
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
        /* Uncomment the code below if you want to run the instrumentation test */
//        EspressoIdlingResource.increment()
        try {
            val response = apiService.getMoviesData(page)
            emit(ApiResponse.Success(response))
            /* Uncomment the code below if you want to run the instrumentation test */
//            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieDetail(movieId: String): Flow<ApiResponse<MovieDetailResponse>> = flow {
        /* Uncomment the code below if you want to run the instrumentation test */
//        EspressoIdlingResource.increment()
        try {
            val response = apiService.getMovieDetail(movieId)
            emit(ApiResponse.Success(response))
            /* Uncomment the code below if you want to run the instrumentation test */
//            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieReviews(movieId: String): Flow<ApiResponse<ReviewResponse>> = flow {
        /* Uncomment the code below if you want to run the instrumentation test */
//        EspressoIdlingResource.increment()
        try {
            val response = apiService.getMovieReviews(movieId)
            emit(ApiResponse.Success(response))
            /* Uncomment the code below if you want to run the instrumentation test */
//            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMovieVideos(movieId: String): Flow<ApiResponse<VideoResponse>> = flow {
        /* Uncomment the code below if you want to run the instrumentation test */
//        EspressoIdlingResource.increment()
        try {
            val response = apiService.getMovieVideos(movieId)
            emit(ApiResponse.Success(response))
            /* Uncomment the code below if you want to run the instrumentation test */
//            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
