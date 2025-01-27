package com.example.movietmdbapp.movie_detail_feature.presentation

import com.example.movietmdbapp.core.domain.model.Movie

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
    data class AddFavorite(val movie: Movie) : MovieDetailEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent()
    data class RemoveFavorite(val movie: Movie) : MovieDetailEvent()
}