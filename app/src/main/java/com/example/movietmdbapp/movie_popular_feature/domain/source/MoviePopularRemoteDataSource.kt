package com.example.movietmdbapp.movie_popular_feature.domain.source

import com.example.movietmdbapp.core.data.remote.response.MovieResponse
import com.example.movietmdbapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {
    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MovieResponse
}