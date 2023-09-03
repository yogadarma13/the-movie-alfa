package com.yogadarma.themoviealfa.navigation

import com.yogadarma.common.navigation.NavigationDirection
import com.yogadarma.main_list.MainListActivity
import com.yogadarma.movie_detail.MovieDetailActivity

val activityMapper = mapOf(
    NavigationDirection.MainList::class.java to MainListActivity::class.java,
    NavigationDirection.MovieDetail::class.java to MovieDetailActivity::class.java
)