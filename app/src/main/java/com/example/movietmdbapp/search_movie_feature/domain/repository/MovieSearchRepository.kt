package com.example.movietmdbapp.search_movie_feature.domain.repository

import androidx.paging.PagingSource
import com.example.movietmdbapp.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getSearchMovies(query: String): PagingSource<Int, MovieSearch>
}