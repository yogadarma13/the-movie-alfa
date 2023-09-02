package com.yogadarma.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.room.MovieDatabase
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.remote.ApiResponse
import com.yogadarma.core.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val database: MovieDatabase,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {

            when (val responseData = remoteDataSource.getMoviesData(page).first()) {
                is ApiResponse.Success -> {
                    val responseList = responseData.value?.results.orEmpty()
                    val endOfPaginationReached = responseList.isEmpty()

                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            localDataSource.deleteRemoteKeys()
                            localDataSource.deleteAllMovies()
                        }
                        val prevKey = if (page == 1) null else page - 1
                        val nextKey = if (endOfPaginationReached) null else page + 1
                        val keys = responseList.map {
                            RemoteKeysEntity(
                                id = (it.id ?: "").toString(),
                                prevKey = prevKey,
                                nextKey = nextKey
                            )
                        }
                        localDataSource.insertAllKeys(keys)

                        val movieList = responseList.map {
                            MovieEntity(
                                (it.id ?: "").toString(),
                                it.posterPath.orEmpty(),
                                it.title.orEmpty(),
                                it.overview.orEmpty(),
                                it.voteAverage ?: 0.0
                            )
                        }
                        localDataSource.insertMovies(movieList)
                    }
                    return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }

                is ApiResponse.Error -> {
                    return MediatorResult.Error(responseData.error ?: Throwable())
                }
            }
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            localDataSource.getRemoteKeysById(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            localDataSource.getRemoteKeysById(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localDataSource.getRemoteKeysById(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}