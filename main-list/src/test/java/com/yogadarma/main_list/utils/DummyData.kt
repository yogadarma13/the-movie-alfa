package com.yogadarma.main_list.utils

import com.yogadarma.core.domain.model.MovieItem

object DummyData {
    fun generateMoviesList() = listOf(
        MovieItem(
            id = "615656",
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            title = "Meg 2= The Trench",
            voteAverage = 7.0
        ),
        MovieItem(
            id = "976573",
            overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
            poster = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
            title = "Elemental",
            voteAverage = 7.8,
        )
    )
}