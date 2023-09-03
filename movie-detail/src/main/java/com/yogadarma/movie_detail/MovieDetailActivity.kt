package com.yogadarma.movie_detail

import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.yogadarma.common.BuildConfig
import com.yogadarma.common.base.BaseActivity
import com.yogadarma.common.extension.changeTimeFormat
import com.yogadarma.common.extension.loadImage
import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.model.Review
import com.yogadarma.movie_detail.bottom_sheet.ReviewsBottomSheet
import com.yogadarma.movie_detail.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity :
    BaseActivity<ActivityMovieDetailBinding>(ActivityMovieDetailBinding::inflate) {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var review: Review? = null
    private var videoKey: String? = null

    private val youTubePlayerListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoKey?.let {
                youTubePlayer.loadVideo(it, 0F)
            }
        }
    }

    override fun onView() {
        val movieId = intent.getStringExtra(BuildConfig.MOVIE_ID)

        movieId?.let {
            getMovieDetailData(it)
            getReviewsData(it)
            getVideoData(it)
        }

        binding.btnAllReviews.setOnClickListener {
            ReviewsBottomSheet.newInstance(review).show(supportFragmentManager)
        }
    }

    private fun getMovieDetailData(movieId: String) {
        viewModel.getMovieDetail(movieId).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    with(binding) {
                        layoutMovieDetail.visibility = View.GONE
                        shimmerMovieDetail.root.visibility = View.VISIBLE
                        shimmerMovieDetail.root.startShimmer()
                    }
                }

                is Resource.Success -> {
                    response.data?.let {
                        with(binding) {
                            layoutMovieDetail.visibility = View.VISIBLE
                            shimmerMovieDetail.root.visibility = View.GONE
                            shimmerMovieDetail.root.stopShimmer()
                        }
                        setMovieDetailData(it)
                    }
                }

                is Resource.Error -> {}
            }
        }
    }

    private fun setMovieDetailData(data: Movie) {
        with(binding) {
            imgDetailPoster.loadImage(BuildConfig.BASE_IMAGE_URL + data.poster.orEmpty())
            tvDetailTitle.text = data.title
            tvDetailGenres.text = data.genres
            tvDetailVoteAverage.text = (data.voteAverage ?: 0.0).toString()
            tvDetailOverview.text = data.overview
            tvDetailReleaseDate.text = data.releaseDate?.changeTimeFormat(
                BuildConfig.DATE_FORMAT_1,
                BuildConfig.DATE_FORMAT_2
            )
        }
    }

    private fun getReviewsData(movieId: String) {
        viewModel.getReviewsData(movieId).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    with(binding) {
                        layoutReview.visibility = View.GONE
                        shimmerMovieReview.root.visibility = View.VISIBLE
                        shimmerMovieReview.root.startShimmer()
                    }
                }

                is Resource.Success -> {
                    review = response.data

                    review?.let {
                        with(binding) {
                            layoutReview.visibility = View.VISIBLE
                            shimmerMovieReview.root.visibility = View.GONE
                            shimmerMovieReview.root.stopShimmer()
                        }

                        setReviewData(it)
                    }
                }

                is Resource.Error -> {}
            }
        }
    }

    private fun setReviewData(review: Review) {
        if (review.list.isNullOrEmpty()) {
            binding.labelReview.visibility = View.GONE
            binding.itemReview.root.visibility = View.GONE
            binding.btnAllReviews.visibility = View.GONE
        } else {
            val firstData = review.list?.get(0)
            binding.labelReview.visibility = View.VISIBLE
            binding.btnAllReviews.visibility = View.VISIBLE
            with(binding.itemReview) {
                root.visibility = View.VISIBLE

                val imageAvatar = if (firstData?.avatar.isNullOrEmpty()) R.drawable.ic_avatar
                else BuildConfig.BASE_IMAGE_URL + firstData?.avatar

                imgAvatar.loadImage(imageAvatar)
                tvUsername.text = firstData?.username
                tvDate.text = firstData?.date?.changeTimeFormat(
                    BuildConfig.DATE_FORMAT_3,
                    BuildConfig.DATE_FORMAT_4
                )
                tvContent.text = firstData?.content
                tvContent.maxLines = 3
                tvContent.ellipsize = TextUtils.TruncateAt.END
            }
        }
    }

    private fun getVideoData(movieId: String) {
        viewModel.getVideoData(movieId).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    with(binding) {
                        layoutVideo.visibility = View.GONE
                        shimmerMovieVideo.root.visibility = View.VISIBLE
                        shimmerMovieVideo.root.startShimmer()
                    }
                }

                is Resource.Success -> {
                    videoKey = response.data?.key

                    videoKey?.let {
                        with(binding) {
                            layoutVideo.visibility = View.VISIBLE
                            shimmerMovieVideo.root.visibility = View.GONE
                            shimmerMovieVideo.root.stopShimmer()
                        }
                        setVideoTrailer()
                    }
                }

                is Resource.Error -> {}
            }
        }
    }

    private fun setVideoTrailer() {
        lifecycle.addObserver(binding.videoTrailer)
        binding.videoTrailer.addYouTubePlayerListener(youTubePlayerListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(binding.videoTrailer)
        binding.videoTrailer.removeYouTubePlayerListener(youTubePlayerListener)
    }
}