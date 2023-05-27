package com.namnp.heroes.presentation.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.presentation.common.ListContent
import com.namnp.heroes.ui.theme.statusBarColor
import com.namnp.heroes.ui.theme.topAppBarBackgroundColor
import com.namnp.heroes.ui.theme.topAppBarContentColor
import com.namnp.heroes.util.Constants

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(coil.annotation.ExperimentalCoilApi::class)
@Composable
fun ListHeroesScreen(
    navController: NavHostController,
    listHeroesViewModel: ListHeroesViewModel = hiltViewModel()
) {
    val allHeroes = listHeroesViewModel.getAllHeroesByCategory.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = MaterialTheme.colors.topAppBarContentColor,
                        )
                    }
                },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
                contentColor = Color.White
            )
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController
            )
        }

    )
}