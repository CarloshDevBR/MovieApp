package com.example.movietmdbapp.movie_detail_feature.data.mapper

import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAverage = voteAverage
    )
}