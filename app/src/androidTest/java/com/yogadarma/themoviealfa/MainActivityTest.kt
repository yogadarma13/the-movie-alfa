package com.yogadarma.themoviealfa

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.yogadarma.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.yogadarma.main_list.R as resourceMainList
import com.yogadarma.movie_detail.R as resourceDetail

class MainActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    /* NOTE: if you want run instrumentation test, you need to uncomment EspressoIdlingResource code
       on class RemoteDataSource */
    @Test
    fun test_mainListPage_and_detailPage() {
        Thread.sleep(3000)

        // Main List Page
        onView(withId(resourceMainList.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(resourceMainList.id.rvMovies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        // Movie Detail Page
        onView(withId(resourceDetail.id.imgDetailPoster)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailTitle)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailGenres)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailVoteAverage)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailReleaseDate)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailOverview)).perform(ViewActions.scrollTo())
        onView(withId(resourceDetail.id.labelOverview)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDetailOverview)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.videoTrailer)).perform(ViewActions.scrollTo())
        onView(withId(resourceDetail.id.labelTrailer)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.videoTrailer)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.btnAllReviews)).perform(ViewActions.scrollTo())
        onView(withId(resourceDetail.id.labelReview)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.imgAvatar)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvUsername)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.tvContent)).check(matches(isDisplayed()))
        onView(withId(resourceDetail.id.btnAllReviews)).check(matches(isDisplayed()))
    }
}