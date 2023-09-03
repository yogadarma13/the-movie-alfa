package com.yogadarma.core.domain.usecase

import androidx.paging.PagingData
import com.yogadarma.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}