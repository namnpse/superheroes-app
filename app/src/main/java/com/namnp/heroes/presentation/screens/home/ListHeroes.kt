package com.namnp.heroes.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.presentation.components.LikeAnimatedButton
import com.namnp.heroes.ui.theme.contrastColor
import com.namnp.heroes.ui.theme.fonts
import com.namnp.heroes.util.Constants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListHeroes(
    heroes: List<Hero?>,
    onClick: (Hero?) -> Unit,
    navController: NavHostController,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        itemsIndexed(heroes) { index, hero ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        onClick(hero)
                    }
                )
                {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                    text = hero?.name ?: "",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(4.dp),
                    color = MaterialTheme.colors.contrastColor, textAlign = TextAlign.Center, fontFamily = fonts,
                )
            }
        }
    }
}