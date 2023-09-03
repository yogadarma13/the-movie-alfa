package com.yogadarma.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogadarma.core.domain.usecase.GetMovieDetailUseCase
import com.yogadarma.core.domain.usecase.GetReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getReviewsUseCase: GetReviewsUseCase
) : ViewModel() {

    fun getMovieDetail(movieId: String) = getMovieDetailUseCase(movieId).asLiveData()

    fun getReviewsData(movieId: String) = getReviewsUseCase(movieId).asLiveData()

}