package com.example.movietmdbapp.movie_popular_feature.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietmdbapp.R
import com.example.movietmdbapp.core.presentation.components.common.MovieAppBar
import com.example.movietmdbapp.core.util.UtilFunctions
import com.example.movietmdbapp.movie_popular_feature.presentation.components.MovieContent
import com.example.movietmdbapp.movie_popular_feature.presentation.state.MoviePopularState

@Composable
fun MoviePopularScreen(uiState: MoviePopularState, navigateToDetailMovie: (Int) -> Unit) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.popular_movies)
        }
    ) { paddingValues ->
        MovieContent(
            pagingMovies = movies,
            paddingValues = paddingValues,
            onClick = { movieId ->
                UtilFunctions.logInfo("MOVIE_ID", movieId.toString())

                navigateToDetailMovie(movieId)
            }
        )
    }
}