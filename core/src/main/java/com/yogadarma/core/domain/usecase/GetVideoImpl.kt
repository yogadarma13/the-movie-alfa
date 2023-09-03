package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Video
import com.yogadarma.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoImpl @Inject constructor(private val repository: Repository) : GetVideoUseCase {
    override fun invoke(movieId: String): Flow<Resource<Video>> = repository.getVideoData(movieId)
}