package com.yogadarma.core.domain.usecase

import androidx.paging.PagingData
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesImpl @Inject constructor(private val repository: Repository) : GetMoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> = repository.getMoviesData()
}