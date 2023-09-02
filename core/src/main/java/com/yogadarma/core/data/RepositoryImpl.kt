package com.yogadarma.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.room.MovieDatabase
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.domain.model.MovieItem
import com.yogadarma.core.domain.repository.Repository
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
    override fun getMoviesData(): Flow<PagingData<MovieItem>> {
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
                MovieItem(it.id, it.poster, it.title, it.overview, it.voteAverage)
            }
        }
    }

}
