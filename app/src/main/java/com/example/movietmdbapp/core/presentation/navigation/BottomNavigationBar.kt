package com.example.movietmdbapp.core.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movietmdbapp.core.route.root.BottomNavItem
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white
import com.example.movietmdbapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSearch,
        BottomNavItem.MovieFavorite
    )

    NavigationBar(
        contentColor = yellow,
        containerColor = black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = yellow,
                    selectedIconColor = white,
                    selectedTextColor = yellow,
                    unselectedIconColor = yellow,
                    unselectedTextColor = yellow,
                    disabledIconColor = black,
                    disabledTextColor = black
                ),
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = "",
                    )
                },
                label = {
                    Text(
                        text = destination.title,
                        color = yellow
                    )
                },
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}