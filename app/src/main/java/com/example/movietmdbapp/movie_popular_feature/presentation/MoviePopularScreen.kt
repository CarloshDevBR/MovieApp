package com.example.movietmdbapp.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietmdbapp.R
import com.example.movietmdbapp.core.util.UtilFunctions
import com.example.movietmdbapp.movie_popular_feature.presentation.components.MovieContent
import com.example.movietmdbapp.movie_popular_feature.presentation.state.MoviePopularState
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(uiState: MoviePopularState, navigateToDetailMovie: (Int) -> Unit) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                }
            )
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