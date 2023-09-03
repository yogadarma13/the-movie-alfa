package com.yogadarma.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.room.MovieDatabase
import com.yogadarma.core.data.source.remote.ApiResponse
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.data.source.remote.model.VideoResponse
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.model.Review
import com.yogadarma.core.domain.model.Video
import com.yogadarma.core.domain.repository.Repository
import com.yogadarma.core.utils.toMovie
import com.yogadarma.core.utils.toMovieEntity
import com.yogadarma.core.utils.toReview
import com.yogadarma.core.utils.toReviewEntity
import com.yogadarma.core.utils.toVideo
import com.yogadarma.core.utils.toVideoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMoviesData(): Flow<PagingData<Movie>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = MovieRemoteMediator(movieDatabase, localDataSource, remoteDataSource),
            pagingSourceFactory = {
                localDataSource.getMoviesData()
            }
        ).flow.mapLatest { pagingData ->
            pagingData.map {
                it.toMovie()
            }
        }
    }

    override fun getMovieDetail(movieId: String): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieDetailResponse>() {
            override suspend fun loadFromDB(): Movie {
                return localDataSource.getMovieDetail(movieId).toMovie()
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            override suspend fun saveCallResult(data: MovieDetailResponse?) {
                val movieEntity = data?.toMovieEntity()
                movieEntity?.let {
                    localDataSource.insertMovieDetail(movieId, it.releaseDate, it.genres)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean = data?.releaseDate.isNullOrEmpty()

        }.asFlow()

    override fun getReviewsData(movieId: String): Flow<Resource<Review>> =
        object : NetworkBoundResource<Review, ReviewResponse>() {
            override suspend fun loadFromDB(): Review? {
                return localDataSource.getReviews(movieId)?.toReview()
            }

            override suspend fun createCall(): Flow<ApiResponse<ReviewResponse>> =
                remoteDataSource.getMovieReviews(movieId)

            override suspend fun saveCallResult(data: ReviewResponse?) {
                val reviewEntity = data?.toReviewEntity(movieId)
                reviewEntity?.let {
                    localDataSource.insertReviews(it)
                }
            }

            override fun shouldFetch(data: Review?): Boolean = data?.list.isNullOrEmpty()

        }.asFlow()

    override fun getVideoData(movieId: String): Flow<Resource<Video>> =
        object : NetworkBoundResource<Video, VideoResponse>() {
            override suspend fun loadFromDB(): Video? {
                return localDataSource.getVideo(movieId)?.toVideo()
            }

            override suspend fun createCall(): Flow<ApiResponse<VideoResponse>> =
                remoteDataSource.getMovieVideos(movieId)

            override suspend fun saveCallResult(data: VideoResponse?) {
                val videoTrailerList = data?.results?.filter { it.type == "Trailer" }
                val videoItem = if (!videoTrailerList.isNullOrEmpty()) videoTrailerList.first()
                else data?.results?.first()

                val videoEntity = videoItem?.toVideoEntity(movieId)

                videoEntity?.let {
                    localDataSource.insertVideo(it)
                }
            }

            override fun shouldFetch(data: Video?): Boolean = data?.key.isNullOrEmpty()

        }.asFlow()

}
