package com.namnp.heroes.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.presentation.common.ListContent
import com.namnp.heroes.presentation.components.RatingWidget
import com.namnp.heroes.presentation.image_slider.AutoSlidingCarousel
import com.namnp.heroes.presentation.screens.details.UiEvent
import com.namnp.heroes.ui.theme.MEDIUM_PADDING
import com.namnp.heroes.ui.theme.SMALL_PADDING
import com.namnp.heroes.ui.theme.statusBarColor
import com.namnp.heroes.ui.theme.topAppBarContentColor
import com.namnp.heroes.util.Constants
import com.namnp.heroes.util.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getMarvelHeroes.collectAsLazyPagingItems()
    val images = homeViewModel.banners.collectAsState().value.map { "${Constants.BASE_URL}${it.image}" }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
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
                val pagerState = rememberPagerState()
                HorizontalPager(count = images.size, state = pagerState) { page ->
                    Card(
                        Modifier
//                            .weight(0.8f)
//                            .size(100.dp)
                            .aspectRatio(1.5f)
//                            .background(color = Purple500)
                            .graphicsLayer {
                                // Calculate the absolute offset for the current page from the
                                // scroll position. We use the absolute value which allows us to mirror
                                // any effects for both directions
//                                val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                                val pageOffset =
                                    ((pagerState.currentPage - page) + pagerState.currentPageOffset).absoluteValue

                                // We animate the alpha, between 50% and 100%
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    ) {
                        // Card content
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(images[page])
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(200.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                customListView(LocalContext.current)
                Spacer(modifier = Modifier.height(100.dp))
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
                Spacer(modifier = Modifier.height(100.dp))
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
                Spacer(modifier = Modifier.height(100.dp))
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
//                ListContent(
//                    heroes = allHeroes,
//                    navController = navController
//                )
            }
        }
    )
}

val Purple200 = Color(0xFF0F9D58)
val Purple500 = Color(0xFF0F9D58)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// in the below line, we are adding different colors.
val greenColor = Color(0xFF0F9D58)


// in the below line, we are creating a
// model class for our data class.
data class ListModel(

    // in the below line, we are creating two variable
    // one is for language name which is string.
    val languageName: String,

    // next variable is for our
    // image which is an integer.
    val languageImg: Int
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun customListView(context: Context) {
    // in the below line, we are creating and
    // initializing our array list
    lateinit var courseList: List<ListModel>
    courseList = ArrayList<ListModel>()

    // in the below line, we are adding data to our list.
    courseList = courseList + ListModel("Android", R.drawable.ic_calendar)
    courseList = courseList + ListModel("JavaScript", R.drawable.ic_cake)
    courseList = courseList + ListModel("Python", R.drawable.ic_search_document)
    courseList = courseList + ListModel("C++", R.drawable.ic_bolt)
    courseList = courseList + ListModel("Java", com.google.android.material.R.drawable.ic_clock_black_24dp)
    courseList = courseList + ListModel("Node Js", R.drawable.greetings)

    // in the below line, we are creating a
    // lazy row for displaying a horizontal list view.
    LazyRow {
        // in the below line, we are setting data for each item of our listview.
        itemsIndexed(courseList) { index, item ->
            // in the below line, we are creating a card for our list view item.
            Card(
                // inside our grid view on below line
                // we are adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        courseList[index].languageName + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // in the below line, we are adding
                // padding from our all sides.
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp),

                // in the below line, we are adding
                // elevation for the card.
                elevation = 6.dp
            )
            {
                // in the below line, we are creating
                // a row for our list view item.
                Column(
                    // for our row we are adding modifier
                    // to set padding from all sides.
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // in the below line, inside row we are adding spacer
                    Spacer(modifier = Modifier.height(5.dp))

                    // in the below line, we are adding Image to display the image.
                    Image(
                        // in the below line, we are specifying the drawable image for our image.
                        painter = painterResource(id = courseList[index].languageImg),

                        // in the below line, we are specifying
                        // content description for our image
                        contentDescription = "img",

                        // in the below line, we are setting height
                        // and width for our image.
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .padding(5.dp),

                        alignment = Alignment.Center
                    )

                    // in the below line, we are adding spacer between image and a text
                    Spacer(modifier = Modifier.height(5.dp))

                    // in the below line, we are creating a text.
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our model class.
                        text = courseList[index].languageName,

                        // in the below line, we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // in the below line, we are adding color for our text
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}