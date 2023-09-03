package com.yogadarma.core.data.source.local

import androidx.paging.PagingSource
import com.yogadarma.core.data.source.local.room.dao.MovieDao
import com.yogadarma.core.data.source.local.room.dao.RemoteKeysDao
import com.yogadarma.core.data.source.local.room.dao.ReviewDao
import com.yogadarma.core.data.source.local.room.dao.VideoDao
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity
import com.yogadarma.core.data.source.local.room.entity.VideoEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteKeysDao: RemoteKeysDao,
    private val reviewDao: ReviewDao,
    private val videoDao: VideoDao
) : LocalDataSource {

    override suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    override suspend fun insertMovieDetail(id: String, releaseDate: String?, genres: String?) =
        movieDao.insertMovieDetail(id, releaseDate, genres)

    override fun getMoviesData(): PagingSource<Int, MovieEntity> = movieDao.getMovies()

    override suspend fun getMovieDetail(id: String): MovieEntity = movieDao.getMovieDetail(id)

    override suspend fun deleteAllMovies() = movieDao.deleteAll()

    override suspend fun insertAllKeys(remoteKey: List<RemoteKeysEntity>) =
        remoteKeysDao.insertAll(remoteKey)

    override suspend fun getRemoteKeysById(id: String): RemoteKeysEntity? =
        remoteKeysDao.getRemoteKeysById(id)

    override suspend fun deleteRemoteKeys() = remoteKeysDao.deleteRemoteKeys()

    override suspend fun insertReviews(review: ReviewEntity) = reviewDao.insertReviews(review)

    override suspend fun getReviews(id: String): ReviewEntity? = reviewDao.getReviews(id)
    override suspend fun insertVideo(videoEntity: VideoEntity) = videoDao.insertVideo(videoEntity)

    override suspend fun getVideo(id: String): VideoEntity? = videoDao.getVideo(id)
}
