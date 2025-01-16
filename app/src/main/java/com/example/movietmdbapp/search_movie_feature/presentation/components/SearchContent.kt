package com.example.movietmdbapp.search_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.core.presentation.components.common.ErrorScreen
import com.example.movietmdbapp.core.presentation.components.common.LoadingView
import com.example.movietmdbapp.movie_popular_feature.presentation.components.MovieItem
import com.example.movietmdbapp.search_movie_feature.presentation.MovieSearchEvent
import com.example.movietmdbapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true

                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]

                movie?.let {
                    MovieItem(
                        voteAvarege = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId ->
                            onDetail(movieId)
                        }
                    )
                }

                isLoading = false
            }

            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false

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
                        isLoading = false

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