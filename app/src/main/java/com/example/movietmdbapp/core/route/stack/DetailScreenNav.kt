package com.example.movietmdbapp.core.route.stack

import com.example.movietmdbapp.core.util.Constants

sealed class DetailScreenNav(val route: String) {
    data object DetailScreen : DetailScreenNav(
        route = "movie_detail_destination?${Constants.ARGUMENTS.MOVIE_DETAIL_ARGUMENT_KEY}={${Constants.ARGUMENTS.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?${Constants.ARGUMENTS.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}