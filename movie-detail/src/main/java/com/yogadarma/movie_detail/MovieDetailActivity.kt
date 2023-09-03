package com.yogadarma.movie_detail

import androidx.activity.viewModels
import com.yogadarma.common.BuildConfig
import com.yogadarma.common.base.BaseActivity
import com.yogadarma.common.extension.changeTimeFormat
import com.yogadarma.common.extension.loadImage
import com.yogadarma.core.data.source.Resource
import com.yogadarma.movie_detail.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity :
    BaseActivity<ActivityMovieDetailBinding>(ActivityMovieDetailBinding::inflate) {

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onView() {
        val movieId = intent.getStringExtra(BuildConfig.MOVIE_ID)

        movieId?.let {
            getMovieDetailData(it)
        }
    }

    private fun getMovieDetailData(movieId: String) {
        viewModel.getMovieDetail(movieId).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    with(binding) {
                        imgDetailPoster.loadImage(BuildConfig.BASE_IMAGE_URL + response.data?.poster.orEmpty())
                        tvDetailTitle.text = response.data?.title
                        tvDetailGenres.text = response.data?.genres
                        tvDetailVoteAverage.text = (response.data?.voteAverage ?: 0.0).toString()
                        tvDetailOverview.text = response.data?.overview
                        tvDetailReleaseDate.text = response.data?.releaseDate?.changeTimeFormat(
                            BuildConfig.DATE_FORMAT_1,
                            BuildConfig.DATE_FORMAT_2
                        )
                    }
                }

                is Resource.Error -> {
                }
            }
        }
    }
}