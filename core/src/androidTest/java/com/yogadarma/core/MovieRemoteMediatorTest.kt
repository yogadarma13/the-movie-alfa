package com.yogadarma.core

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.yogadarma.core.data.MovieRemoteMediator
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.room.MovieDatabase
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.remote.ApiResponse
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.utils.DummyData
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalPagingApi
@RunWith(MockitoJUnitRunner::class)
class MovieRemoteMediatorTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private var mockDb: MovieDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        MovieDatabase::class.java
    ).allowMainThreadQueries().build()

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val dummyDataFlow =
            flow { emit(ApiResponse.Success(DummyData.generateMoviesListResponse())) }
        Mockito.`when`(remoteDataSource.getMoviesData(1)).thenReturn(dummyDataFlow)

        val remoteMediator = MovieRemoteMediator(
            mockDb,
            localDataSource,
            remoteDataSource
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }
}