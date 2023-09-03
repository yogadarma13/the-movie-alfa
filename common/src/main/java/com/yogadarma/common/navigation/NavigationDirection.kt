package com.yogadarma.common.navigation

import com.yogadarma.common.BuildConfig

sealed class NavigationDirection(val extras: Map<String, Any?>) {
    object MainList : NavigationDirection(mapOf())
    class MovieDetail(movieId: String) : NavigationDirection(mapOf(BuildConfig.MOVIE_ID to movieId))
}