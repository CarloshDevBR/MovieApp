package com.example.movietmdbapp.movie_detail_feature.domain.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails

    fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>
}