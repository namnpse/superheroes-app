package com.namnp.heroes.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.namnp.heroes.R
import com.namnp.heroes.presentation.components.BottomNavigationBar
import com.namnp.heroes.presentation.screens.favorite.FavoriteScreen
import com.namnp.heroes.presentation.screens.home.*
import com.namnp.heroes.presentation.screens.profile.ProfileScreen

@Composable
fun MainScreen(
    appNavController: NavHostController,
) {
    val navBottomNavigationController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navBottomNavigationController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    appNavController = appNavController,
                    navBottomNavigationController = navBottomNavigationController
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
    navBottomNavigationController: NavHostController
) {
    NavHost(navBottomNavigationController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = appNavController)
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen(appNavController)
        }
    }
}