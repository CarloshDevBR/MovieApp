package com.example.movietmdbapp.movie_detail_feature.domain.source

import com.example.movietmdbapp.core.domain.model.MovieDetails
import com.example.movietmdbapp.core.domain.model.MoviePaging
import com.example.movietmdbapp.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging

    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}