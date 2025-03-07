package com.example.movietmdbapp.search_movie_feature.data.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.core.paging.MovieSearchPagingSource
import com.example.movietmdbapp.search_movie_feature.domain.repository.MovieSearchRepository
import com.example.movietmdbapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchRepositoryImpl constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {
    override fun getSearchMovies(query: String): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}