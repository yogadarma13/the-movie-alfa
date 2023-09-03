package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface GetVideoUseCase {
    operator fun invoke(movieId: String): Flow<Resource<Video>>
}