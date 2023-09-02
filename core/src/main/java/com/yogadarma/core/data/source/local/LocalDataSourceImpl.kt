package com.yogadarma.core.data.source.local

import androidx.paging.PagingSource
import com.yogadarma.core.data.source.local.room.dao.MovieDao
import com.yogadarma.core.data.source.local.room.dao.RemoteKeysDao
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteKeysDao: RemoteKeysDao
) : LocalDataSource {

    override suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    override fun getMoviesData(): PagingSource<Int, MovieEntity> = movieDao.getMovies()

    override suspend fun deleteAllMovies() = movieDao.deleteAll()

    override suspend fun insertAllKeys(remoteKey: List<RemoteKeysEntity>) =
        remoteKeysDao.insertAll(remoteKey)

    override suspend fun getRemoteKeysById(id: String): RemoteKeysEntity? =
        remoteKeysDao.getRemoteKeysById(id)

    override suspend fun deleteRemoteKeys() = remoteKeysDao.deleteRemoteKeys()
}
