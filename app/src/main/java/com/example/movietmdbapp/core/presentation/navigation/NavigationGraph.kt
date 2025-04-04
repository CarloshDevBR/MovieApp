package com.example.movietmdbapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movietmdbapp.core.route.root.BottomNavItem
import com.example.movietmdbapp.core.route.stack.DetailScreenNav
import com.example.movietmdbapp.core.util.Constants
import com.example.movietmdbapp.movie_detail_feature.presentation.MovieDetailViewModel
import com.example.movietmdbapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import com.example.movietmdbapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import com.example.movietmdbapp.movie_popular_feature.presentation.MoviePopularScreen
import com.example.movietmdbapp.movie_popular_feature.presentation.MoviePopularViewModel
import com.example.movietmdbapp.movie_detail_feature.presentation.MovieDetailScreen
import com.example.movietmdbapp.search_movie_feature.presentation.MovieSearchScreen
import com.example.movietmdbapp.search_movie_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(route = BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }

        composable(route = BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent = viewModel::event
            val onFetch = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                },
            )
        }

        composable(route = BottomNavItem.MovieFavorite.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState.movies.collectAsStateWithLifecycle(initialValue = emptyList())

            MovieFavoriteScreen(
                movies = uiState.value,
                navigateToDetailMovie = { movieId ->
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId))
                }
            )
        }

        composable(
            route = DetailScreenNav.DetailScreen.route,
            arguments = listOf(
                navArgument(Constants.ARGUMENTS.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onAddFavorite = viewModel::onAddFavorite

            MovieDetailScreen(
                uiState = uiState,
                onAddFavorite = onAddFavorite,
            )
        }
    }
}