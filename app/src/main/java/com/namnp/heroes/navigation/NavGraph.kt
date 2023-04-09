package com.namnp.heroes.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.namnp.heroes.presentation.screens.details.DetailsScreen
import com.namnp.heroes.presentation.screens.home.HomeScreen
import com.namnp.heroes.presentation.screens.home.MoviesScreen
import com.namnp.heroes.presentation.screens.home.MusicScreen
import com.namnp.heroes.presentation.screens.home.NavigationItem
import com.namnp.heroes.presentation.screens.list.ListHeroesScreen
import com.namnp.heroes.presentation.screens.main.MainScreen
import com.namnp.heroes.presentation.screens.search.SearchScreen
import com.namnp.heroes.presentation.screens.splash.SplashScreen
import com.namnp.heroes.presentation.screens.welcome.WelcomeScreen
import com.namnp.heroes.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalCoilApi
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    val navBarNavController = rememberNavController()
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
//        composable(route = Screen.HomeScreen.route) {
//            HomeScreen(navController = navController)
//        }
        composable(
            route = Screen.HeroDetailsScreen.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.ListHeroesScreen.route) {
            ListHeroesScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navBarNavController)
        }
    }
}