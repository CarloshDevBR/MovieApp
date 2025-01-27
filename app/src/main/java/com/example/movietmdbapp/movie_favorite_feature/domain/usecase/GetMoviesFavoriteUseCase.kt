package com.example.movietmdbapp.movie_favorite_feature.domain.usecase

import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}