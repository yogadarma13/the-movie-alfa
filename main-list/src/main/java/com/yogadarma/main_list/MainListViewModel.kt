package com.yogadarma.main_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yogadarma.core.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase): ViewModel() {

    fun getMoviesData() = getMoviesUseCase().asLiveData().cachedIn(viewModelScope)
}