package com.example.movietmdbapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(route = BottomNavItem.MoviePopular.route) {

        }

        composable(route = BottomNavItem.MovieSearch.route) {

        }

        composable(route = BottomNavItem.MovieFavorite.route) {

        }
    }
}