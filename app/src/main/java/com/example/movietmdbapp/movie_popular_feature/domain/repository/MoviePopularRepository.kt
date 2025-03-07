package com.example.movietmdbapp.movie_popular_feature.domain.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.Movie

interface MoviePopularRepository {
    fun getPopularMovies(): PagingSource<Int, Movie>
}