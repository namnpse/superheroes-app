package com.namnp.heroes.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.ui.theme.Purple500
import com.namnp.heroes.ui.theme.Purple700
import com.namnp.heroes.ui.theme.statusBarColor

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val currentUser = splashViewModel.currentUser
    val degrees = remember { Animatable(0f) }
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            if(currentUser == null) {
                navController.navigate(Screen.LoginScreen.route)
            } else {
                navController.navigate(Screen.MainScreen.route)
            }
        } else {
            navController.navigate(Screen.WelcomeScreen.route)
        }
    }
    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val modifier = if (MaterialTheme.colors.isLight) {
        Modifier.background(
            Brush.verticalGradient(listOf(Purple700, Purple500))
        )
    } else {
        Modifier.background(Color.Black)
    }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.shield),
            contentDescription = stringResource(R.string.app_logo),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(1f)
}