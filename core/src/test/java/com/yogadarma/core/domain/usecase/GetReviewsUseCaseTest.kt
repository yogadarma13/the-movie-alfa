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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetReviewsUseCaseTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var getReviewsImpl: GetReviewsImpl

    @Before
    fun setup() {
        getReviewsImpl = GetReviewsImpl(repository)
    }

    @Test
    fun `when getReviews UseCase Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateReviewsData()
        val dummyDataFlow = flow { emit(Resource.Success(dummyData)) }

        Mockito.`when`(repository.getReviewsData("615656")).thenReturn(dummyDataFlow)

        getReviewsImpl("615656").test {
            awaitItem().let {
                assertTrue(it is Resource.Success)
                assertTrue(it is Resource.Success)
                assertEquals(dummyData.list?.get(0)?.reviewId, it.data?.list?.get(0)?.reviewId)
                assertEquals(dummyData.list?.get(0)?.avatar, it.data?.list?.get(0)?.avatar)
                assertEquals(dummyData.list?.get(0)?.date, it.data?.list?.get(0)?.date)
                assertEquals(dummyData.list?.get(0)?.username, it.data?.list?.get(0)?.username)
                assertEquals(dummyData.list?.get(0)?.content, it.data?.list?.get(0)?.content)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}