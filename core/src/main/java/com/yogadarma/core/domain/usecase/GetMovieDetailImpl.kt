package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Movie
import com.yogadarma.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailImpl @Inject constructor(private val repository: Repository): GetMovieDetailUseCase {
    override fun invoke(movieId: String): Flow<Resource<Movie>> = repository.getMovieDetail(movieId)
}