package com.yogadarma.movie_detail.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogadarma.common.BuildConfig
import com.yogadarma.common.base.BaseBottomSheet
import com.yogadarma.common.extension.getParcelableData
import com.yogadarma.core.domain.model.Review
import com.yogadarma.movie_detail.adapter.ReviewItemAdapter
import com.yogadarma.movie_detail.databinding.BottomSheetReviewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReviewsBottomSheet : BaseBottomSheet<BottomSheetReviewsBinding>() {

    @Inject
    lateinit var reviewItemAdapter: ReviewItemAdapter

    private var review: Review? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetReviewsBinding
        get() = BottomSheetReviewsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        review = arguments?.getParcelableData(BuildConfig.REVIEW_DATA)
    }

    override fun onView() {
        initAdapter()
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    private fun initAdapter() {
        review?.let {
            binding.rvReviews.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = reviewItemAdapter
            }
            reviewItemAdapter.addItems(it.list.orEmpty())
        }
    }

    companion object {
        val TAG: String = ReviewsBottomSheet::class.java.simpleName

        @JvmStatic
        fun newInstance(review: Review?) =
            ReviewsBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable(BuildConfig.REVIEW_DATA, review)
                }
            }
    }
}