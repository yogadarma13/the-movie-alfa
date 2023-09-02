package com.yogadarma.main_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yogadarma.common.BuildConfig.BASE_IMAGE_URL
import com.yogadarma.common.extension.loadImage
import com.yogadarma.core.domain.model.MovieItem
import com.yogadarma.main_list.databinding.ItemMovieBinding
import javax.inject.Inject

class MovieItemAdapter @Inject constructor() :
    PagingDataAdapter<MovieItem, MovieItemAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieItem) {
            with(binding) {
                imgPoster.loadImage(BASE_IMAGE_URL + data.poster)
                tvTitle.text = data.title
                tvVoteAverage.text = data.voteAverage.toString()
                tvOverview.text = data.overview
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}