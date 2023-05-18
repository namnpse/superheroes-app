package com.namnp.heroes.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.ui.theme.*
import com.namnp.heroes.util.Constants

@Composable
fun ProfileScreen(
    navController: NavHostController,
    themeState: MutableState<ThemeState>,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )
//    val currentUser = profileViewModel.currentUser
    val displayColor = MaterialTheme.colors.Purple500_White
//    profileViewModel.getUser()
    val getUserResponse = profileViewModel.user.collectAsState().value
    if (getUserResponse is Response.Failure) {
        getUserResponse.apply {
            LaunchedEffect(error) {
                println(error.message)
            }
        }
    }
    var user: User? = null
    if(getUserResponse is Response.Success){
        getUserResponse.data?.let {
            user = it
        }
    }

    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Boolean>("update")?.observeForever {
            println("savedStateHandle ${it.toString()}")
            if(it) {
                profileViewModel.getUser()
            }
        }
//    secondScreenResult?.value?.let {
//        // Read the result
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
//            .wrapContentSize(Alignment.Center)
    ) {
        Row {
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.UpdateProfileScreen.route)
                    },
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit profile",
                tint = Purple500,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .error(R.drawable.avatar_placeholder)
//                    .data("${Constants.BASE_URL}/images/urashiki.jpg")
                    .data(user?.photoUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING, bottom = MEDIUM_PADDING)
                    .weight(3f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(160.dp))
//                    .background(Purple500),
            )
            Column(
                modifier = Modifier
                    .weight(7f),
            ) {
                Text(
                    text = user?.nickName ?: "",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                    color = displayColor,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "@${user?.email}",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                    color = displayColor,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier
                    .size(PROFILE_ICON_SIZE)
                    .alpha(ContentAlpha.medium),
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone",
                tint = displayColor,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                color = displayColor,
                text = user?.phone ?: "(+84) 123456789",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
        Divider(
            color = colorResource(id = R.color.ink100s).copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = MEDIUM_PADDING)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = displayColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Your favorites",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
                color = displayColor,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
                imageVector = Icons.Default.DarkMode,
                tint = displayColor,
                contentDescription = "DarkMode"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Dark Theme",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
                color = displayColor,
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = themeState.value.theme == Theme.Dark,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Purple500,
//                    checkedTrackColor = Color.ShimmerLightGray
                ),
                onCheckedChange = { checked ->
                    themeState.value = ThemeState(if (checked) Theme.Dark else Theme.Light)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
                imageVector = Icons.Default.Settings,
                tint = displayColor,
                contentDescription = "Settings"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Settings",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
                color = displayColor,
            )
        }
        if (profileViewModel.currentUser != null) {
            Divider(
                color = colorResource(id = R.color.ink100s).copy(alpha = 0.5f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = MEDIUM_PADDING)
            )
            Row(
                modifier = Modifier
                    .padding(start = MEDIUM_PADDING)
                    .clickable {
                        profileViewModel.logOut()
                        navController.navigate(Screen.LoginScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    modifier = Modifier.size(PROFILE_ICON_SIZE),
                    imageVector = Icons.Default.Logout,
                    tint = Color.Red,
                    contentDescription = "Logout"
                )
                Spacer(modifier = Modifier.width(MEDIUM_PADDING))
                Text(
                    text = "Log out",
                    modifier = Modifier.padding(vertical = MEDIUM_PADDING),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                    color = Color.Red,
                )
            }
        }
        Spacer(Modifier.weight(1f))
        if (profileViewModel.currentUser == null)
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = MEDIUM_PADDING),
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple500,
                    contentColor = Color.White
                ),
//            border = BorderStroke(1.dp, displayColor),
                shape = RoundedCornerShape(50),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = LARGE_PADDING, vertical = 4.dp),
                    text = "Log In",
                    fontFamily = fonts,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                )
            }
    }
}