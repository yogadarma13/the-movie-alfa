package com.yogadarma.movie_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.usecase.GetMovieDetailUseCase
import com.yogadarma.core.domain.usecase.GetReviewsUseCase
import com.yogadarma.core.domain.usecase.GetVideoUseCase
import com.yogadarma.movie_detail.utils.DummyData
import com.yogadarma.movie_detail.utils.MainDispatcherRule
import com.yogadarma.movie_detail.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    private lateinit var getReviewsUseCase: GetReviewsUseCase

    @Mock
    private lateinit var getVideoUseCase: GetVideoUseCase

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setup() {
        viewModel = MovieDetailViewModel(getMovieDetailUseCase, getReviewsUseCase, getVideoUseCase)
    }

    @Test
    fun `when getMovieDetail ViewModel Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateMovieData()
        val dummyDataFlow = flow { emit(Resource.Success(dummyData)) }

        `when`(getMovieDetailUseCase("1234")).thenReturn(dummyDataFlow)

        val actualResult = viewModel.getMovieDetail("1234").getOrAwaitValue()

        assertTrue(actualResult is Resource.Success)
        assertEquals(dummyData.id, actualResult.data?.id)
        assertEquals(dummyData.poster, actualResult.data?.poster)
        assertEquals(dummyData.title, actualResult.data?.title)
        assertEquals(dummyData.overview, actualResult.data?.overview)
        assertEquals(dummyData.voteAverage, actualResult.data?.voteAverage)
        assertEquals(dummyData.releaseDate, actualResult.data?.releaseDate)
        assertEquals(dummyData.genres, actualResult.data?.genres)

        verify(getMovieDetailUseCase).invoke("1234")
    }

    @Test
    fun `when getReviewsData ViewModel Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateReviewsData()
        val dummyDataFlow = flow { emit(Resource.Success(dummyData)) }

        `when`(getReviewsUseCase("1234")).thenReturn(dummyDataFlow)

        val actualResult = viewModel.getReviewsData("1234").getOrAwaitValue()

        assertTrue(actualResult is Resource.Success)
        assertEquals(dummyData.list?.get(0)?.reviewId, actualResult.data?.list?.get(0)?.reviewId)
        assertEquals(dummyData.list?.get(0)?.avatar, actualResult.data?.list?.get(0)?.avatar)
        assertEquals(dummyData.list?.get(0)?.date, actualResult.data?.list?.get(0)?.date)
        assertEquals(dummyData.list?.get(0)?.username, actualResult.data?.list?.get(0)?.username)
        assertEquals(dummyData.list?.get(0)?.content, actualResult.data?.list?.get(0)?.content)

        verify(getReviewsUseCase).invoke("1234")
    }
}