package com.namnp.heroes.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.namnp.heroes.R
import com.namnp.heroes.presentation.components.BottomNavigationBar
import com.namnp.heroes.presentation.screens.favorite.FavoriteScreen
import com.namnp.heroes.presentation.screens.home.HomeScreen
import com.namnp.heroes.presentation.screens.home.NavigationItem
import com.namnp.heroes.presentation.screens.profile.ProfileScreen
import com.namnp.heroes.ui.theme.ThemeState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    appNavController: NavHostController,
    themeState: MutableState<ThemeState>,
) {
//    val navBottomNavigationController = rememberNavController()
    val mainScreens = listOf(
        NavigationItem.Home,
        NavigationItem.Favorite,
        NavigationItem.Profile,
    )
    val pagerState = rememberPagerState()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
//            navBottomNavigationController,
                pagerState,
                mainScreens,
            )
        },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
//                Navigation(
//                    appNavController = appNavController,
//                    navBottomNavigationController = navBottomNavigationController,
//                    themeState = themeState
//                )
                ViewPagerScreen(
                    pagerState = pagerState,
                    appNavController = appNavController,
                    themeState = themeState
                )
            }
        },
        backgroundColor = colorResource(R.color.colorPrimaryDark) // Set background color to avoid the white flashing when you switch between screens
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
//fun BottomNavGraph(
fun ViewPagerScreen(
    pagerState: PagerState,
    appNavController: NavHostController,
    themeState: MutableState<ThemeState>,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> HomeScreen(navController = appNavController)
                1 -> FavoriteScreen()
                2 -> ProfileScreen(appNavController, themeState = themeState)
            }
        }
    }
}

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun Navigation(
//    appNavController: NavHostController,
//    navBottomNavigationController: NavHostController,
//    themeState: MutableState<ThemeState>,
//) {
//    NavHost(navBottomNavigationController, startDestination = NavigationItem.Home.route) {
//        composable(NavigationItem.Home.route) {
//            HomeScreen(navController = appNavController)
//        }
//        composable(NavigationItem.Favorite.route) {
//            FavoriteScreen()
//        }
//        composable(NavigationItem.Profile.route) {
//            ProfileScreen(appNavController, themeState = themeState)
//        }
//    }
//}