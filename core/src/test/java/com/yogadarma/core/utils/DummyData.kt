package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.room.entity.MovieEntity
import com.yogadarma.core.data.source.local.room.entity.RemoteKeysEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewColumn
import com.yogadarma.core.data.source.local.room.entity.ReviewEntity
import com.yogadarma.core.data.source.local.room.entity.ReviewItemColumn
import com.yogadarma.core.data.source.remote.model.AuthorDetails
import com.yogadarma.core.data.source.remote.model.MovieDetailResponse
import com.yogadarma.core.data.source.remote.model.MovieListItem
import com.yogadarma.core.data.source.remote.model.MovieListResponse
import com.yogadarma.core.data.source.remote.model.ReviewItemResponse
import com.yogadarma.core.data.source.remote.model.ReviewResponse
import com.yogadarma.core.domain.model.Movie

object DummyData {
    fun generateMoviesListResponse() = MovieListResponse(
        page = 1, totalPages = 123, totalResults = 1234, results = listOf(
            MovieListItem(
                adult = false,
                backdropPath = "/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
                genreIds = listOf(28, 878, 27),
                id = 615656,
                originalLanguage = "en",
                originalTitle = "Meg 2= The Trench",
                overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
                popularity = 6913.755,
                posterPath = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
                releaseDate = "2023-08-02",
                title = "Meg 2= The Trench",
                video = false,
                voteAverage = 7.0,
                voteCount = 1174
            ),
            MovieListItem(
                adult = false,
                backdropPath = "/jZIYaISP3GBSrVOPfrp98AMa8Ng.jpg",
                genreIds = listOf(
                    16,
                    35,
                    10751,
                    14,
                    10749
                ),
                id = 976573,
                originalLanguage = "en",
                originalTitle = "Elemental",
                overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
                popularity = 1782.085,
                posterPath = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
                releaseDate = "2023-06-14",
                title = "Elemental",
                video = false,
                voteAverage = 7.8,
                voteCount = 1648
            )
        )
    )

    fun generateMoviesListEntity() = listOf(
        MovieEntity(
            id = "615656",
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            title = "Meg 2= The Trench",
            voteAverage = 7.0
        ),
        MovieEntity(
            id = "976573",
            overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
            poster = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
            title = "Elemental",
            voteAverage = 7.8,
        )
    )

    fun generateRemoteKeysEntityList() = listOf(
        RemoteKeysEntity(
            id = "key1",
            prevKey = 1,
            nextKey = 2
        ),
        RemoteKeysEntity(
            id = "key2",
            prevKey = 2,
            nextKey = 3
        )
    )

    fun generateMoviesList() = listOf(
        Movie(
            id = "615656",
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            title = "Meg 2= The Trench",
            voteAverage = 7.0
        ),
        Movie(
            id = "976573",
            overview = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental= how much they have in common.",
            poster = "/rvSzdzwfRjAeESh1BPIxjf4eklN.jpg",
            title = "Elemental",
            voteAverage = 7.8,
        )
    )

    fun generateMovieDetailResponse() = MovieDetailResponse(
        adult = false,
        backdropPath = "/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
        id = 615656,
        originalLanguage = "en",
        originalTitle = "Meg 2= The Trench",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        popularity = 6913.755,
        posterPath = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        releaseDate = "2023-08-02",
        title = "Meg 2= The Trench",
        video = false,
        voteAverage = 7.0,
        voteCount = 1174
    )

    fun generateMovieEntity() = MovieEntity(
        id = "615656",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        releaseDate = "2023-08-02",
        title = "Meg 2= The Trench",
        voteAverage = 7.0,
        genres = "Action, Drama"
    )

    fun generateMovieData() = Movie(
        id = "615656",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        poster = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        releaseDate = "2023-08-02",
        title = "Meg 2= The Trench",
        voteAverage = 7.0,
        genres = "Action, Drama"
    )

    fun generateReviewsResponse() = ReviewResponse(
        id = 615656,
        page = 1,
        results = listOf(
            ReviewItemResponse(
                author = "CinemaSerf",
                authorDetails = AuthorDetails(
                    name = "CinemaSerf",
                    username = "Geronimo1967",
                    avatarPath = "/1kks3YnVkpyQxzw36CObFPvhL5f.jpg",
                    rating = 5
                ),
                content = "Now the \"Meg\" (2018) itself could never be called a good film, but it is a great deal better than this muddled and derivative effort. \"Jonas\" (Jason Statham) is now working as a sort of eco-policeman trying to stop the dumping of toxic waste into the oceans, and after a snappy \"007\" style opening, he is daringly rescued by \"Mac\" (Clff Curtis) and \"Rigas\" (Melissanthu Mahut) and returned to the research centre where he is reunited with \"Jess\" (Skyler Samuels), \"DJ\" (Page Kennedy) and the adrenalin seeking \"Juiming\" (Jing Wu) who are nursing the daughter of the last megalodon! The team now travel to a remote installation where they must investigate some more of the beasties that live below the frozen layer put there by nature to ensure than we don't mix. Thing is, it seems they are not the only folks who've hit on the idea that there might be untapped riches 25,000 feet below the surface, and soon our team are involved in a contretemps with \"Montes\" (Sergio Peris-Mencheta) that introduces treachery, double-dealing and causes explosions galore that release not just megs, but also an enormous octopus into the ocean where they can merrily terrorise the holidaymakers on the nearby resort of \"Fun Island\". Can \"Jonas\" et al manage to take on four of these super-creatures before they've snacked their way through the tourists? This might have been a bit better if they'd just cut all the preamble and gone straight to the rig and to the underwater action, big fish and pyrotechnics. As it is, we spend far too long meandering about on the surface meeting the characters and there's way too much pointless dialogue throughout - though one or two half-hearted witticisms and puns help a little - before an ending that is entirely predictable and really rather processionally so. The acting is just banal, the continuity is all over the place - as is the editing - and the huge chunk of Ali Baba money that's floated this thing ensures that the switches from the English to Chinese languages actually smacks more of keeping everybody happy in the boardroom rather than engaging anyone in the actual cinema. Simply, even the charismatic Statham cannot rescue this from the doldrums of CGI-led mediocrity that churns out an unremarkable hybrid of \"Jaws\" and \"Jurassic Park\". It does need a big screen. If you wait til it's on the telly then you will be even more disappointed. Mind you, is that actually possible?",
                createdAt = "2023-08-12T06:21:56.587Z",
                id = "64d72504d100b6011c8180f6",
                updatedAt = "2023-08-12T06:21:56.728Z",
                url = "https://www.themoviedb.org/review/64d72504d100b6011c8180f6"
            ),
            ReviewItemResponse(
                author = "MovieGuys",
                authorDetails = AuthorDetails(
                    name = "",
                    username = "MovieGuys",
                    avatarPath = null,
                    rating = 5
                ),
                content = "Meg 2 doesn't really feel like a follow up film, to its 2018 counterpart. Indeed, the latest instalment feels more like a platform for various action \"stunts\". \r\n\r\nThe giant,prehistoric shark's are essentially window dressing, for a variety of frenetic action sequences, where things blow up, are torn apart, shot at, harpooned, people gobbled up Jaws and Jurassic Park style and what I can only describe as acrobatic, aquatic scenes with jet ski's and the like, all take place. They even throw in a giant squid, to spice things up. \r\n\r\nOr put more simply, this is a messy mash up, of derivative, often dispirit ideas. Unsurprisingly, the results a bit chaotic, with no compass to direct the viewer, in terms of the story, such as it is. Its quite watchable, in its own way but it never really goes anywhere.\r\n\r\nIn summary, watchable on a very visually superficial level. Lots of action but little in the way of an established story, to pull the whole thing together.",
                createdAt = "2023-08-26T19:41:42.368Z",
                id = "64ea55765258ae00add50b7d",
                updatedAt = "2023-08-26T19:45:44.496Z",
                url = "https://www.themoviedb.org/review/64ea55765258ae00add50b7d"
            ),
        )
    )

    fun generateReviewEntity() = ReviewEntity(
        id = "615656",
        review = ReviewColumn(
            reviewList = listOf(
                ReviewItemColumn(
                    "1234",
                    "tes",
                    "2023-12-23",
                    "tes.jpg",
                    "Content Review"
                )
            )
        )
    )
}