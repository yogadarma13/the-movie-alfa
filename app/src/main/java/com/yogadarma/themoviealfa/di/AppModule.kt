package com.yogadarma.themoviealfa.di

import com.yogadarma.core.domain.usecase.GetMovieDetailImpl
import com.yogadarma.core.domain.usecase.GetMovieDetailUseCase
import com.yogadarma.core.domain.usecase.GetMoviesImpl
import com.yogadarma.core.domain.usecase.GetMoviesUseCase
import com.yogadarma.core.domain.usecase.GetReviewsImpl
import com.yogadarma.core.domain.usecase.GetReviewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsGetMoviesUseCase(getMoviesImpl: GetMoviesImpl): GetMoviesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsGetMovieDetailUseCase(getMovieDetailImpl: GetMovieDetailImpl): GetMovieDetailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsGetReviewsUseCase(getReviewsImpl: GetReviewsImpl): GetReviewsUseCase
}