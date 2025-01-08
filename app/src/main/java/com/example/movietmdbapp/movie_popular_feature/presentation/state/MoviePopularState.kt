package com.example.movietmdbapp.movie_popular_feature.presentation.state

import androidx.paging.PagingData
import com.example.movietmdbapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)
