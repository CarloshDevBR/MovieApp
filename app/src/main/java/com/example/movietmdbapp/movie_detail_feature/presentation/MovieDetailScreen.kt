package com.example.movietmdbapp.movie_detail_feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietmdbapp.R
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.presentation.components.common.MovieAppBar
import com.example.movietmdbapp.movie_detail_feature.presentation.components.MovieDetailContent
import com.example.movietmdbapp.movie_detail_feature.presentation.state.MovieDetailState
import com.example.movietmdbapp.ui.theme.black

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit,
) {
    val pagingMovieSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.detail_movie)
        },
        containerColor = black,
        content = { paddingValues ->
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMovieSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = { movie ->
                    onAddFavorite(movie)
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}