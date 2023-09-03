package com.yogadarma.main_list

import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogadarma.common.base.BaseActivity
import com.yogadarma.common.navigation.NavigationDirection
import com.yogadarma.main_list.adapter.LoadingStateAdapter
import com.yogadarma.main_list.adapter.MovieItemAdapter
import com.yogadarma.main_list.databinding.ActivityMainListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainListActivity : BaseActivity<ActivityMainListBinding>(ActivityMainListBinding::inflate) {

    @Inject
    lateinit var movieItemAdapter: MovieItemAdapter

    private val viewModel: MainListViewModel by viewModels()

    override fun onView() {
        initAdapter()
        initPagingRefreshLoadState()
        initLiveData()
    }

    private fun initAdapter() {
        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(this@MainListActivity)
            setHasFixedSize(true)
            adapter = movieItemAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    movieItemAdapter.retry()
                }
            )
        }

        movieItemAdapter.setOnItemClickListener { movieId ->
            navigateTo(NavigationDirection.MovieDetail(movieId))
        }
    }

    private fun initLiveData() {
        viewModel.getMoviesData().observe(this) {
            movieItemAdapter.submitData(lifecycle, it)
        }
    }

    private fun initPagingRefreshLoadState() {
        binding.layRefresh.btnRetry.setOnClickListener {
            movieItemAdapter.refresh()
        }

        lifecycleScope.launch {
            movieItemAdapter.loadStateFlow.collectLatest { loadState ->
                binding.apply {
                    layRefresh.root.isVisible =
                        loadState.refresh is LoadState.Error && movieItemAdapter.snapshot().items.isEmpty()

                    rvMovies.visibility =
                        if (loadState.refresh !is LoadState.Loading && loadState.refresh !is LoadState.Error ||
                            movieItemAdapter.snapshot().items.isNotEmpty()
                        ) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                    if (loadState.refresh is LoadState.Loading && movieItemAdapter.snapshot().items.isEmpty()) {
                        shimmerMovie.root.visibility = View.VISIBLE
                        shimmerMovie.root.startShimmer()
                    } else {
                        shimmerMovie.root.visibility = View.GONE
                        shimmerMovie.root.stopShimmer()
                    }

                    if (loadState.refresh is LoadState.NotLoading && movieItemAdapter.snapshot().items.isEmpty()) {
                        tvEmptyData.visibility = View.VISIBLE
                    } else {
                        tvEmptyData.visibility = View.GONE
                    }
                }
            }
        }
    }
}