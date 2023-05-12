package com.namnp.heroes.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.namnp.heroes.R
import com.namnp.heroes.presentation.components.BottomNavigationBar
import com.namnp.heroes.presentation.screens.favorite.FavoriteScreen
import com.namnp.heroes.presentation.screens.home.HomeScreen
import com.namnp.heroes.presentation.screens.home.NavigationItem
import com.namnp.heroes.presentation.screens.profile.ProfileScreen
import com.namnp.heroes.ui.theme.ThemeState

@Composable
fun MainScreen(
    appNavController: NavHostController,
    themeState: MutableState<ThemeState>,
) {
    val navBottomNavigationController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navBottomNavigationController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    appNavController = appNavController,
                    navBottomNavigationController = navBottomNavigationController,
                    themeState = themeState
                )
            }
        },
        backgroundColor = colorResource(R.color.colorPrimaryDark) // Set background color to avoid the white flashing when you switch between screens
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(
    appNavController: NavHostController,
    navBottomNavigationController: NavHostController,
    themeState: MutableState<ThemeState>,
) {
    NavHost(navBottomNavigationController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = appNavController)
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen(appNavController, themeState = themeState)
        }
    }
}