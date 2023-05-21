package com.namnp.heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.namnp.heroes.navigation.SetupNavGraph
import com.namnp.heroes.ui.theme.HeroesAppTheme
import com.namnp.heroes.ui.theme.ThemeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = mainViewModel.themeState.collectAsState()
            HeroesAppTheme(
                themeState = themeState.value,
            ) {
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    themeState = themeState as MutableState<ThemeState>,
                )
            }
        }
    }
}