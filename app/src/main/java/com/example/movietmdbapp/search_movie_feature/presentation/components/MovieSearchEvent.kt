package com.example.movietmdbapp.search_movie_feature.presentation.components

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}