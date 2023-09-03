package com.yogadarma.movie_detail.bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yogadarma.common.base.BaseBottomSheet
import com.yogadarma.movie_detail.adapter.ReviewItemAdapter
import com.yogadarma.movie_detail.databinding.BottomSheetReviewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReviewsBottomSheet : BaseBottomSheet<BottomSheetReviewsBinding>() {

    @Inject
    lateinit var reviewItemAdapter: ReviewItemAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetReviewsBinding
        get() = BottomSheetReviewsBinding::inflate

    override fun onView() {
    }
}