package com.yogadarma.main_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.yogadarma.core.domain.model.MovieItem
import com.yogadarma.core.domain.usecase.GetMoviesUseCase
import com.yogadarma.main_list.utils.DummyData
import com.yogadarma.main_list.utils.MainDispatcherRule
import com.yogadarma.main_list.utils.getOrAwaitValue
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
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var viewModel: MainListViewModel

    @Before
    fun setup() {
        viewModel = MainListViewModel(getMoviesUseCase)
    }

    @Test
    fun `when getMoviesData ViewModel Should Not Null and Return Data`() = runTest {
        val dummyData = DummyData.generateMoviesList()
        val dummyDataPaging = MoviePagingSource.snapshot(dummyData)
        val dummyDataFlow = flow { emit(dummyDataPaging) }

        Mockito.`when`(getMoviesUseCase()).thenReturn(dummyDataFlow)

        val actualResult: PagingData<MovieItem> =
            viewModel.getMoviesData().getOrAwaitValue()

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

        verify(getMoviesUseCase).invoke()
    }
}

class MoviePagingSource : PagingSource<Int, LiveData<List<MovieItem>>>() {
    companion object {
        fun snapshot(items: List<MovieItem>): PagingData<MovieItem> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<MovieItem>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<MovieItem>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }
}