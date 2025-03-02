package com.example.movietmdbapp.core.domain.paging

import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MoviePaging

class MoviePagingFactory {
    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            Movie(
                id = 2,
                title = "John Wick",
                voteAverage = 7.9,
                imageUrl = "Url"
            )
        ),
    )
}