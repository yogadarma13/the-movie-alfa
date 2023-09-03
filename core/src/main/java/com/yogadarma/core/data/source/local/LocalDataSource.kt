package com.yogadarma.core.data.source.local

import androidx.paging.PagingSource
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity

interface LocalDataSource {

    suspend fun insertMovies(movies: List<MovieEntity>)

    suspend fun insertMovieDetail(id: String, releaseDate: String?, genres: String?)

    fun getMoviesData(): PagingSource<Int, MovieEntity>

    suspend fun getMovieDetail(id: String): MovieEntity

    suspend fun deleteAllMovies()

    suspend fun insertAllKeys(remoteKey: List<RemoteKeysEntity>)

    suspend fun getRemoteKeysById(id: String): RemoteKeysEntity?

    suspend fun deleteRemoteKeys()

    suspend fun insertReviews(review: ReviewEntity)

    suspend fun getReviews(id: String): ReviewEntity?
}
