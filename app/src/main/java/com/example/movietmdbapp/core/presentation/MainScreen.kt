package com.example.movietmdbapp.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.movietmdbapp.core.presentation.navigation.BottomNavigationBar
import com.example.movietmdbapp.core.presentation.navigation.NavigationGraph
import com.example.movietmdbapp.core.presentation.navigation.currentRoute
import com.example.movietmdbapp.core.route.stack.DetailScreenNav

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute(navController = navController) != DetailScreenNav.DetailScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(navController = navController)
            }
        }
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}