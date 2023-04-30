package com.namnp.heroes.navigation

import com.namnp.heroes.util.Constants.CATEGORY_ARGUMENT_KEY
import com.namnp.heroes.util.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object WelcomeScreen : Screen("welcome_screen")
    object HomeScreen : Screen("home_screen")
    object HeroDetailsScreen : Screen("details_screen/{$DETAILS_ARGUMENT_KEY}") {
        fun passHeroId(heroId: Int): String {
            return "details_screen/$heroId"
        }
    }

    object ListHeroesScreen : Screen("list_heroes_screen/{$CATEGORY_ARGUMENT_KEY}") {
        fun passCategoryId(categoryId: String): String {
            return "list_heroes_screen/$categoryId"
        }
    }
    object SearchScreen : Screen("search_screen")
    object MainScreen : Screen("main_screen")
    object LoginScreen : Screen("login_screen")
}
