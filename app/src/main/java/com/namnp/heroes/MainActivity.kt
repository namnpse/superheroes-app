package com.namnp.heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.namnp.heroes.navigation.SetupNavGraph
import com.namnp.heroes.ui.theme.HeroesAppTheme
import com.namnp.heroes.ui.theme.Theme
import com.namnp.heroes.ui.theme.ThemeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = remember { mutableStateOf(ThemeState(Theme.Light)) }
            HeroesAppTheme(
//                darkTheme = true,
                themeState = themeState.value,
            ) {
                navController = rememberNavController()
                SetupNavGraph(navController = navController, themeState = themeState)
            }
        }
    }
}