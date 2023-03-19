package com.namnp.heroes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.namnp.heroes.presentation.screens.SplashScreen
import com.namnp.heroes.util.Constants.DETAILS_HERO_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.WelcomeScreen.route) {

        }
        composable(route = Screen.HomeScreen.route) {

        }
        composable(
            route = Screen.HeroDetailsScreen.route,
            arguments = listOf(navArgument(DETAILS_HERO_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.SearchScreen.route) {

        }
    }
}