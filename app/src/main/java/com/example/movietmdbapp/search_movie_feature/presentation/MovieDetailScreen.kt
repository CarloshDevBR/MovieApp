package com.example.movietmdbapp.search_movie_feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movietmdbapp.R
import com.example.movietmdbapp.movie_detail_feature.presentation.MovieDetailEvent
import com.example.movietmdbapp.movie_detail_feature.presentation.components.MovieDetailContent
import com.example.movietmdbapp.movie_detail_feature.presentation.state.MovieDetailState
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    id: Int?,
    uiState: MovieDetailState,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit,
) {
    val pagingMovieSimilar = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        if (id != null) {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_movie),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
        },
        containerColor = black,
        content = { paddingValues ->
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMovieSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = {

                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}