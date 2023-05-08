package com.namnp.heroes.presentation.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.util.Constants

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {

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
            .background(colorResource(id = R.color.colorPrimaryDark))
//            .wrapContentSize(Alignment.TopCenter)
    ) {
        Text(
            text = "Favorite Heroes",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(favoriteHeroes) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Number")
                    Text(text = "  ${it?.name}")
                }
            }
        }
    }
}