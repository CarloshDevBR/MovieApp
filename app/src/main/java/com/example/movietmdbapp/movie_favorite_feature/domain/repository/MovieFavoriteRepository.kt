package com.example.movietmdbapp.movie_favorite_feature.domain.repository

import com.example.movietmdbapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository {
    fun getMovies(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean
}