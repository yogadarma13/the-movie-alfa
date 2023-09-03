package com.yogadarma.core.data.source.remote.network

import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getMoviesData(@Query("page") page: Int): MovieListResponse

    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: String): MovieDetailResponse

    @GET("{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: String): ReviewResponse
}
