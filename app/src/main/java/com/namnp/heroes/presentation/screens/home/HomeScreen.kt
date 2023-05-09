package com.namnp.heroes.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.presentation.components.LikeAnimatedButton
import com.namnp.heroes.presentation.image_slider.AutoSlidingCarousel
import com.namnp.heroes.ui.theme.*
import com.namnp.heroes.util.Constants

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val images =
        homeViewModel.banners.collectAsState().value.map { "${Constants.BASE_URL}${it.image}" }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor,
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.SearchScreen.route)
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
//                        .padding(16.dp)
                        .height(200.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    AutoSlidingCarousel(
                        itemsCount = images.size,
                        itemContent = { index ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(images[index])
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(200.dp)
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                HeroListView(LocalContext.current, homeViewModel, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroListView(context: Context, homeViewModel: HomeViewModel, navController: NavHostController) {

    val borutoHeroes =
        homeViewModel.getAllHeroes.collectAsLazyPagingItems().itemSnapshotList.take(8)
    val marvelHeroes =
        homeViewModel.getMarvelHeroes.collectAsLazyPagingItems().itemSnapshotList.take(8)

    // in the below line, we are creating a
    // lazy row for displaying a horizontal list view.
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Boruto heroes",
            modifier = Modifier
//            .width(120.dp)
                .padding(4.dp),
            color = MaterialTheme.colors.contrastColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            fontFamily = fonts,
        )
        Text(
            text = "See more",
            modifier = Modifier
//            .width(120.dp)
                .padding(4.dp)
                .clickable {
                    navController.navigate(Screen.ListHeroesScreen.passCategoryId("Boruto"))
                },
            color = MaterialTheme.colors.contrastColor,
            textAlign = TextAlign.Center,
            fontFamily = fonts,
//            fontWeight = FontWeight.W400,
//            style = MaterialTheme.typography.h6
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
//        contentPadding = PaddingValues(16.dp)
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        itemsIndexed(borutoHeroes) { index, hero ->
            Column(
                modifier = Modifier
//                        .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
//                        Toast.makeText(
//                            context,
//                            hero?.name + " selected..",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        navController.navigate(Screen.HeroDetailsScreen.passHeroId(heroId = hero?.id ?: 0))
                    },
                )
                {
                    Column(
                        modifier = Modifier
//                        .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                    Spacer(modifier = Modifier.height(5.dp))
                        Box {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("${Constants.BASE_URL}${hero?.image}")
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(120.dp)
//                            .aspectRatio(0.5f)
                            )
                            LikeAnimatedButton(
                                modifier = Modifier.align(Alignment.TopEnd)
                                    .fillMaxWidth()
                                    .size(40.dp)
                                    .padding(8.dp),
                                hero = hero,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = borutoHeroes[index]?.name ?: "",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(4.dp),
                    color = MaterialTheme.colors.contrastColor, textAlign = TextAlign.Center, fontFamily = fonts,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Marvel heroes",
            modifier = Modifier
//            .width(120.dp)
                .padding(4.dp),
            color = MaterialTheme.colors.contrastColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            fontFamily = fonts,
        )
        Text(
            text = "See more",
            modifier = Modifier
//            .width(120.dp)
                .padding(4.dp)
                .clickable {
                    navController.navigate(Screen.ListHeroesScreen.passCategoryId("Marvel"))
                },
            color = MaterialTheme.colors.contrastColor,
            textAlign = TextAlign.Center,
            fontFamily = fonts,
//            fontWeight = FontWeight.W400,
//            style = MaterialTheme.typography.h6
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
//        contentPadding = PaddingValues(16.dp)
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        itemsIndexed(marvelHeroes) { index, hero ->
            Column(
                modifier = Modifier
//                        .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
//                        Toast.makeText(
//                            context,
//                            marvelHeroes[index]?.name + " selected..",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        navController.navigate(
                            Screen.HeroDetailsScreen.passHeroId(
                                heroId = hero?.id ?: 0
                            )
                        )
                    },
                )
                {
                    Column(
                        modifier = Modifier
//                        .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                    Spacer(modifier = Modifier.height(5.dp))
                        Box {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("${Constants.BASE_URL}${marvelHeroes[index]?.image}")
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(120.dp)
//                            .aspectRatio(0.5f)
                            )
                            LikeAnimatedButton(
                                modifier = Modifier.align(Alignment.TopEnd)
                                    .fillMaxWidth()
                                    .size(40.dp)
                                    .padding(8.dp),
                                hero = hero,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = marvelHeroes[index]?.name ?: "",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(4.dp),
                    color = MaterialTheme.colors.contrastColor, textAlign = TextAlign.Center, fontFamily = fonts,
                )
            }
        }
    }
}