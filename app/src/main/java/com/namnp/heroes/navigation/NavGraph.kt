package com.namnp.heroes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.namnp.heroes.presentation.screens.home.HomeScreen
import com.namnp.heroes.presentation.screens.search.SearchScreen
import com.namnp.heroes.presentation.screens.splash.SplashScreen
import com.namnp.heroes.presentation.screens.welcome.WelcomeScreen
import com.namnp.heroes.util.Constants.DETAILS_HERO_KEY

@ExperimentalCoilApi
@OptIn(ExperimentalPagerApi::class)
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
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.HeroDetailsScreen.route,
            arguments = listOf(navArgument(DETAILS_HERO_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}