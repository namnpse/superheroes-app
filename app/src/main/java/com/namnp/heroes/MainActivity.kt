package com.namnp.heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.namnp.heroes.navigation.SetupNavGraph
import com.namnp.heroes.presentation.screens.main.Navigation
import com.namnp.heroes.ui.theme.HeroesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeroesAppTheme(
//                darkTheme = true
            ) {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

interface UnidirectionalViewModel<STATE, EVENT, EFFECT> {
    val state: StateFlow<STATE>
    val effect: SharedFlow<EFFECT>
    fun event(event: EVENT)
}

interface NewsListContract :
    UnidirectionalViewModel<NewsListContract.State, NewsListContract.Event,
            NewsListContract.Effect> {

    data class State(
        val news: List<News> = listOf(),
        val refreshing: Boolean = false,
        val showFavoriteList: Boolean = false,
    )

    sealed class Event {
        data class OnFavoriteClick(val news: News) : Event()
        data class OnGetNewsList(val showFavoriteList: Boolean) : Event()
        data class OnSetShowFavoriteList(val showFavoriteList: Boolean) : Event()
        object OnRefresh: Event()
        object OnBackPressed : Event()
        data class ShowToast(val message: String) : Event()
    }

    sealed class Effect {
        object OnBackPressed : Effect()
        data class ShowToast(val message: String) : Effect()
    }
}

data class News(val id: Int)