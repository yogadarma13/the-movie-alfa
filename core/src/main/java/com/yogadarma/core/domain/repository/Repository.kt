package com.yogadarma.core.domain.repository

import androidx.paging.PagingData
import com.yogadarma.core.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getMoviesData(): Flow<PagingData<MovieItem>>
}