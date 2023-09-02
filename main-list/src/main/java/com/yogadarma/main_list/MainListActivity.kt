package com.yogadarma.main_list

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogadarma.common.base.BaseActivity
import com.yogadarma.main_list.databinding.ActivityMainListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainListActivity : BaseActivity<ActivityMainListBinding>(ActivityMainListBinding::inflate) {

    @Inject
    lateinit var movieItemAdapter: MovieItemAdapter

    private val viewModel: MainListViewModel by viewModels()

    override fun onView() {
        initAdapter()
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
    }

    private fun initLiveData() {
        viewModel.getMoviesData().observe(this) {
            movieItemAdapter.submitData(lifecycle, it)
        }
    }
}