package com.example.movietmdbapp.search_movie_feature.data.source

import com.example.movietmdbapp.core.data.remote.MovieService
import com.example.movietmdbapp.core.data.remote.response.SearchResponse
import com.example.movietmdbapp.core.paging.MovieSearchPagingSource
import com.example.movietmdbapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.serachMovie(page = page, query = query)
    }
}