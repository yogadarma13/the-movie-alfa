package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Review
import com.yogadarma.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsImpl @Inject constructor(private val repository: Repository) : GetReviewsUseCase {
    override fun invoke(movieId: String): Flow<Resource<Review>> =
        repository.getReviewsData(movieId)
}