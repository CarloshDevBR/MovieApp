package com.example.movietmdbapp.movie_popular_feature.data.mapper

import com.example.movietmdbapp.core.data.remote.model.MovieResult
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map { movieResult ->
        Movie(
            id = movieResult.id,
            title = movieResult.title,
            voteAverage = movieResult.voteAverage,
            imageUrl = movieResult.posterPath.toPostUrl()
        )
    }