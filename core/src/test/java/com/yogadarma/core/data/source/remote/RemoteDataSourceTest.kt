package com.yogadarma.core.data.source.remote

import app.cash.turbine.test
import com.yogadarma.core.data.source.remote.network.ApiService
import com.yogadarma.core.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {
    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setup() {
        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getMoviesListData when api service success return Result Success`() = runTest {
        val dummyData = DummyData.generateMoviesListResponse()

        `when`(apiService.getMoviesData(1)).thenReturn(dummyData)

        remoteDataSourceImpl.getMoviesData(1).test {
            awaitItem().let {
                Assert.assertTrue(it is ApiResponse.Success)
                assertEquals(dummyData, (it as ApiResponse.Success).value)
                assertEquals(dummyData.page, it.value?.page)
                assertEquals(dummyData.totalPages, it.value?.totalPages)
                assertEquals(dummyData.totalResults, it.value?.totalResults)
                assertEquals(dummyData.results?.get(0), it.value?.results?.get(0))
                assertEquals(dummyData.results?.get(1), it.value?.results?.get(1))
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getMoviesListData when api service error return Result Error`() = runTest {

        `when`(apiService.getMoviesData(1)).thenThrow(HttpException::class.java)

        remoteDataSourceImpl.getMoviesData(1).test {
            Assert.assertTrue(awaitItem() is ApiResponse.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}