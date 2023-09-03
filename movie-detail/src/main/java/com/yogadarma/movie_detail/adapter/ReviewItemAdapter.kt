package com.yogadarma.movie_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yogadarma.common.BuildConfig
import com.yogadarma.common.extension.changeTimeFormat
import com.yogadarma.common.extension.loadImage
import com.yogadarma.core.domain.model.ReviewItem
import com.yogadarma.movie_detail.R
import com.yogadarma.movie_detail.databinding.ItemReviewBinding
import javax.inject.Inject

class ReviewItemAdapter @Inject constructor() :
    RecyclerView.Adapter<ReviewItemAdapter.ViewHolder>() {

    private val dataList: ArrayList<ReviewItem> = arrayListOf()

    fun addItems(newData: List<ReviewItem>) {
        val diffCallback = DiffUtilCallback(dataList, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        dataList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ReviewItem) {
            with(binding) {
                val imageAvatar = if (data.avatar.isNullOrEmpty()) R.drawable.ic_avatar
                else BuildConfig.BASE_IMAGE_URL + data.avatar

                imgAvatar.loadImage(imageAvatar)
                tvUsername.text = data.username
                tvDate.text = data.date?.changeTimeFormat(
                    BuildConfig.DATE_FORMAT_3,
                    BuildConfig.DATE_FORMAT_4
                )
                tvContent.text = data.content
            }
        }
    }
}