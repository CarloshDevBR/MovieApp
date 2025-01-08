package com.example.movietmdbapp.search_movie_feature.data.mapper

import com.example.movietmdbapp.core.data.remote.model.SearchResult
import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map { searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPostUrl(),
        voteAverage = searchResult.voteAverage
    )
}