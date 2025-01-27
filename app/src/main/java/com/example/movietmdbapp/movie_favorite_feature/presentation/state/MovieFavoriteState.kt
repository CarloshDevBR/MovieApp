package com.example.movietmdbapp.movie_favorite_feature.presentation.state

import com.example.movietmdbapp.core.domain.model.Movie

data class MovieFavoriteState(
    val movies: List<Movie> = emptyList()
)
