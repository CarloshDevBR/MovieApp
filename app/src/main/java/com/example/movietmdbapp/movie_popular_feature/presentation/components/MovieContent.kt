package com.example.movietmdbapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.presentation.components.common.ErrorScreen
import com.example.movietmdbapp.core.presentation.components.common.LoadingView

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Unit
) {
    Box(modifier = modifier.background(Color.Black)) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            items(pagingMovies.itemCount) { index ->
                val item = pagingMovies[index]

                item?.let {
                    MovieItem(
                        id = it.id,
                        voteAvarege = it.voteAverage,
                        imageUrl = it.imageUrl,
                        onClick = { movieId ->
                            onClick(movieId)
                        }
                    )
                }
            }

            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                            ErrorScreen(
                                message = "Verifique a sua conexão com a internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                            ErrorScreen(
                                message = "Verifique a sua conexão com a internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}