package com.example.movietmdbapp.movie_popular_feature.data.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.paging.MoviePagingSource
import com.example.movietmdbapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.example.movietmdbapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePopularRepositoryImpl constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}