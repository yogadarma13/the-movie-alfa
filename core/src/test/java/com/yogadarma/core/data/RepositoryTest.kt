package com.yogadarma.core.data

import app.cash.turbine.test
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.room.MovieDatabase
import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.remote.ApiResponse
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var movieDatabase: MovieDatabase

    private lateinit var repository: RepositoryImpl

    @Before
    fun setup() {
        repository = RepositoryImpl(movieDatabase, remoteDataSource, localDataSource)
    }

    @Test
    fun `when getMovieDetail Repository Should Return Movie Data`() = runTest {
        val dummyData = DummyData.generateMovieEntity()

        `when`(localDataSource.getMovieDetail("615656")).thenReturn(dummyData)

        repository.getMovieDetail("615656").test {
            awaitItem() // Return Loading
            awaitItem().let {
                Assert.assertTrue(it is Resource.Success)
                Assert.assertEquals(dummyData.id, it.data?.id)
                Assert.assertEquals(dummyData.poster, it.data?.poster)
                Assert.assertEquals(dummyData.title, it.data?.title)
                Assert.assertEquals(dummyData.overview, it.data?.overview)
                Assert.assertEquals(dummyData.voteAverage, it.data?.voteAverage)
                Assert.assertEquals(dummyData.releaseDate, it.data?.releaseDate)
                Assert.assertEquals(dummyData.genres, it.data?.genres)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when getMovieDetail Repository Should Return Error`() = runTest {

        val dummyDataError = flow<ApiResponse<MovieDetailResponse>> {
            emit(ApiResponse.Error(error = Throwable(message = "error")))
        }

        `when`(localDataSource.getMovieDetail("1234")).thenReturn(MovieEntity("1234"))
        `when`(remoteDataSource.getMovieDetail("1234")).thenReturn(dummyDataError)

        repository.getMovieDetail("1234").test {
            awaitItem() // Return Loading
            awaitItem().let {
                Assert.assertTrue(it is Resource.Error)
                Assert.assertEquals("error", it.error?.message)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when getReviewsData Repository Should Return Movie Data`() = runTest {
        val dummyData = DummyData.generateReviewEntity()

        `when`(localDataSource.getReviews("615656")).thenReturn(dummyData)

        repository.getReviewsData("615656").test {
            awaitItem() // Return Loading
            awaitItem().let {
                Assert.assertTrue(it is Resource.Success)
                Assert.assertEquals(
                    dummyData.review?.reviewList?.get(0)?.reviewId,
                    it.data?.list?.get(0)?.reviewId
                )
                Assert.assertEquals(
                    dummyData.review?.reviewList?.get(0)?.avatar,
                    it.data?.list?.get(0)?.avatar
                )
                Assert.assertEquals(
                    dummyData.review?.reviewList?.get(0)?.date,
                    it.data?.list?.get(0)?.date
                )
                Assert.assertEquals(
                    dummyData.review?.reviewList?.get(0)?.username,
                    it.data?.list?.get(0)?.username
                )
                Assert.assertEquals(
                    dummyData.review?.reviewList?.get(0)?.content,
                    it.data?.list?.get(0)?.content
                )
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when getReviewsData Repository Should Return Error`() = runTest {

        val dummyDataError = flow<ApiResponse<ReviewResponse>> {
            emit(ApiResponse.Error(error = Throwable(message = "error")))
        }

        `when`(localDataSource.getReviews("1234")).thenReturn(null)
        `when`(remoteDataSource.getMovieReviews("1234")).thenReturn(dummyDataError)

        repository.getReviewsData("1234").test {
            awaitItem() // Return Loading
            awaitItem().let {
                Assert.assertTrue(it is Resource.Error)
                Assert.assertEquals("error", it.error?.message)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}