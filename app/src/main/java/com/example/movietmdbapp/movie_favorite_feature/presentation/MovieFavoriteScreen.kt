package com.example.movietmdbapp.movie_favorite_feature.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movietmdbapp.R
import com.example.movietmdbapp.core.presentation.components.common.MovieAppBar
import com.example.movietmdbapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import com.example.movietmdbapp.movie_favorite_feature.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.favorite_movies)
        },
        content = { paddingValues ->
            MovieFavoriteContent(
                movies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )
}

@Preview
@Composable
private fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        uiState = MovieFavoriteState(),
        navigateToDetailMovie = {}
    )
}