package com.example.movietmdbapp.movie_favorite_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movietmdbapp.R
import com.example.movietmdbapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import com.example.movietmdbapp.movie_favorite_feature.presentation.state.MovieFavoriteState
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.favorite_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
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