package com.yogadarma.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.repository.Repository
import com.yogadarma.core.utils.DummyData
import com.yogadarma.core.utils.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var getMoviesImpl: GetMoviesImpl

    @Before
    fun setup() {
        getMoviesImpl = GetMoviesImpl(repository)
    }

    @Test
    fun `when getMoviesData UseCase Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateMoviesList()
        val dummyDataPaging = MoviePagingSource.snapshot(dummyData)
        val dummyDataFlow = flow { emit(dummyDataPaging) }

        `when`(repository.getMoviesData()).thenReturn(dummyDataFlow)

        getMoviesImpl().test {
            awaitItem().let { actualResult ->
                val differ = AsyncPagingDataDiffer(
                    diffCallback = DIFF_CALLBACK,
                    updateCallback = noopListUpdateCallback,
                    workerDispatcher = Dispatchers.Main,
                )
                differ.submitData(actualResult)

                assertNotNull(differ.snapshot())
                assertEquals(dummyData.size, differ.snapshot().size)
                assertEquals(dummyData[0], differ.snapshot()[0])
                assertEquals(dummyData[1], differ.snapshot()[1])
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}

class MoviePagingSource : PagingSource<Int, LiveData<List<Movie>>>() {
    companion object {
        fun snapshot(items: List<Movie>): PagingData<Movie> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<Movie>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<Movie>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}