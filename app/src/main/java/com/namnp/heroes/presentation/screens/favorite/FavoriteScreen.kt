package com.namnp.heroes.presentation.screens.favorite

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.presentation.components.LikeAnimatedButton
import com.namnp.heroes.ui.theme.contrastColor
import com.namnp.heroes.ui.theme.fonts
import com.namnp.heroes.ui.theme.statusBarColor
import com.namnp.heroes.ui.theme.welcomeScreenBackgroundColor
import com.namnp.heroes.util.Constants

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )
    val context = LocalContext.current

    // on below line we are creating a new broad cast manager.
    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        // we will receive data updates in onReceive method.
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent
            val isClear = intent.getStringExtra("clear")
            // on below line we are updating the data in our text view.
            if(isClear == "true") {
                viewModel.clearListFavoriteHeroes()
            }
        }
    }
    // on below line we are registering our local broadcast manager.
    LocalBroadcastManager.getInstance(context).registerReceiver(
        broadcastReceiver, IntentFilter("clear-list-favorite-heroes-local-broadcast")
    )

    val favoriteHeroesResponse = viewModel.favoriteHeroes.collectAsState().value
    if (favoriteHeroesResponse is Response.Failure) {
        favoriteHeroesResponse.apply {
            LaunchedEffect(error) {
                println(error.message)
            }
        }
    }
    var favoriteHeroes = emptyList<Hero?>()
    if(favoriteHeroesResponse is Response.Success){
        favoriteHeroesResponse.data?.let {
            favoriteHeroes = it
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
//            .wrapContentSize(Alignment.TopCenter)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(favoriteHeroes) { hero ->
                FavoriteHeroItem(
                    hero = hero,
                    onClickHero = {

                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteHeroItem(
    hero: Hero?,
    onClickHero: () -> Unit,
) {
    Column(
        modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            onClick = onClickHero,
        )
        {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${Constants.BASE_URL}${hero?.image}")
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(240.dp)
//                            .width(120.dp)
//                            .aspectRatio(0.5f)
                    )
                    LikeAnimatedButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(40.dp)
                            .padding(8.dp),
                        hero = hero,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = hero?.name ?: "",
            modifier = Modifier
                .width(120.dp)
                .padding(4.dp),
            color = MaterialTheme.colors.contrastColor, textAlign = TextAlign.Center, fontFamily = fonts,
        )
    }
}
