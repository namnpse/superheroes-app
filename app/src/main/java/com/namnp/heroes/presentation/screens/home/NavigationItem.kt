package com.namnp.heroes.presentation.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.namnp.heroes.R

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Home")
    object Favorite : NavigationItem("favorite", Icons.Default.Favorite, "Favorite")
    object Profile : NavigationItem("profile", Icons.Default.Person, "Profile")
}