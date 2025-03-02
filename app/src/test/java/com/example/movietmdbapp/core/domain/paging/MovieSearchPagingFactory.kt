package com.example.movietmdbapp.core.domain.paging

import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.core.domain.model.MovieSearchPaging

class MovieSearchPagingFactory {
    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url",
            ),
            MovieSearch(
                id = 2,
                voteAverage = 7.9,
                imageUrl = "Url"
            )
        ),
    )
}