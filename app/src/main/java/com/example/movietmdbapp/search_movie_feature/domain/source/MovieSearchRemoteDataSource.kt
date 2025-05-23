package com.example.movietmdbapp.search_movie_feature.domain.source

import com.example.movietmdbapp.core.domain.model.MovieSearchPaging
import com.example.movietmdbapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource

    suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging
}