package com.example.movietmdbapp.movie_detail_feature.data.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieDetails
import com.example.movietmdbapp.core.paging.MovieSimilarPagingSource
import com.example.movietmdbapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.example.movietmdbapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId = movieId)
    }

    override fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(movieId = movieId, remoteDataSource = remoteDataSource)
    }
}