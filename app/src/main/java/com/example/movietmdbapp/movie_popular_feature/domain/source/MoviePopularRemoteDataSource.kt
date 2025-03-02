package com.example.movietmdbapp.movie_popular_feature.domain.source

import com.example.movietmdbapp.core.domain.model.MoviePaging
import com.example.movietmdbapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {
    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MoviePaging
}