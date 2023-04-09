package com.namnp.heroes.presentation.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.presentation.common.ListContent
import com.namnp.heroes.ui.theme.statusBarColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(coil.annotation.ExperimentalCoilApi::class)
@Composable
fun ListHeroesScreen(
    navController: NavHostController,
    listHeroesViewModel: ListHeroesViewModel = hiltViewModel()
) {
    val allHeroes = listHeroesViewModel.getMarvelHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController
            )
        }

    )
}