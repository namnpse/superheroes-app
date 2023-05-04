package com.namnp.heroes.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.namnp.heroes.R
import com.namnp.heroes.presentation.screens.home.NavigationItem
import com.namnp.heroes.ui.theme.Purple500
import com.namnp.heroes.ui.theme.fonts

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorite,
        NavigationItem.Profile,
    )
    val bgColor = if (isSystemInDarkTheme()) {
        Color.Black
    } else {
        Purple500
    }
    val contentColor = if (isSystemInDarkTheme()) Purple500 else Color.White
    BottomNavigation(
        backgroundColor = bgColor,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (item.title == "Home")
                        Icon(
                            painterResource(id = R.drawable.ic_bolt),
                            contentDescription = item.title
                        )
                    else Icon(item.icon, contentDescription = item.title)
                },
                label = { Text(text = item.title, fontFamily = fonts,) },
                selectedContentColor = contentColor,
                unselectedContentColor = contentColor.copy(0.6f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
}