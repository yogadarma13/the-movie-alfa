package com.yogadarma.core.domain.usecase

import app.cash.turbine.test
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.repository.Repository
import com.yogadarma.core.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var getMovieDetailImpl: GetMovieDetailImpl

    @Before
    fun setup() {
        getMovieDetailImpl = GetMovieDetailImpl(repository)
    }

    @Test
    fun `when getMovieDetail UseCase Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateMovieData()
        val dummyDataFlow = flow { emit(Resource.Success(dummyData)) }

        `when`(repository.getMovieDetail("615656")).thenReturn(dummyDataFlow)

        getMovieDetailImpl("615656").test {
            awaitItem().let {
                assertTrue(it is Resource.Success)
                assertEquals(dummyData.id, it.data?.id)
                assertEquals(dummyData.poster, it.data?.poster)
                assertEquals(dummyData.title, it.data?.title)
                assertEquals(dummyData.overview, it.data?.overview)
                assertEquals(dummyData.voteAverage, it.data?.voteAverage)
                assertEquals(dummyData.releaseDate, it.data?.releaseDate)
                assertEquals(dummyData.genres, it.data?.genres)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

}