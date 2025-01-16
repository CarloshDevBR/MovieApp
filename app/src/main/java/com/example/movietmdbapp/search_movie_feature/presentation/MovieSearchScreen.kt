package com.example.movietmdbapp.search_movie_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietmdbapp.R
import com.example.movietmdbapp.search_movie_feature.presentation.components.SearchContent
import com.example.movietmdbapp.search_movie_feature.presentation.state.MovieSearchState
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.search_movies), color = white)
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = black)
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetail = {
                    navigateToDetailMovie(it)
                }
            )
        }
    )
}