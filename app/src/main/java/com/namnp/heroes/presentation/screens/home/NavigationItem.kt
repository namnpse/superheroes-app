package com.namnp.heroes.presentation.screens.home

import com.namnp.heroes.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_bolt, "Home")
    object Favorite : NavigationItem("favorite", R.drawable.ic_cake, "Favorite")
    object Profile : NavigationItem("profile", R.drawable.ic_calendar, "Profile")
}