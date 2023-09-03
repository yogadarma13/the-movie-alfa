package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface GetReviewsUseCase {
    operator fun invoke(movieId: String): Flow<Resource<Review>>
}