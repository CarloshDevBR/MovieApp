package com.example.movietmdbapp.core.domain.model

data class MoviePaging(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<Movie>
)