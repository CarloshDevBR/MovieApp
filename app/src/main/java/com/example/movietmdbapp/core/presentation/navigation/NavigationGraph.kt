package com.example.movietmdbapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movietmdbapp.movie_popular_feature.presentation.MoviePopularScreen
import com.example.movietmdbapp.movie_popular_feature.presentation.MoviePopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(route = BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(uiState = uiState, navigateToDetailMovie = {})
        }

        composable(route = BottomNavItem.MovieSearch.route) {

        }

        composable(route = BottomNavItem.MovieFavorite.route) {

        }
    }
}