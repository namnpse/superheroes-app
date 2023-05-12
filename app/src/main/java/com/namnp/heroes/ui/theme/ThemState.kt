package com.namnp.heroes.ui.theme

enum class Theme {
    Light, Dark
}

data class ThemeState(var theme: Theme) {
    fun toggleDarkMode() {
        theme = if (theme == Theme.Light) Theme.Dark else Theme.Light
    }
}
