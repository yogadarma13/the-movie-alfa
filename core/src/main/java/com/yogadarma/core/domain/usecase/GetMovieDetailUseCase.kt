package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    operator fun invoke(movieId: String): Flow<Resource<Movie>>
}