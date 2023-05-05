package com.namnp.heroes.util

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.namnp.heroes.ui.theme.Purple500
import com.namnp.heroes.ui.theme.contrastColor
import com.namnp.heroes.ui.theme.welcomeScreenBackgroundColor

@Composable
fun DarkThemTextFieldColors(
    textColor: Color = MaterialTheme.colors.contrastColor,
    disabledTextColor: Color = MaterialTheme.colors.contrastColor,
    backgroundColor: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    cursorColor: Color = Purple500,
    errorCursorColor: Color = MaterialTheme.colors.contrastColor,
    focusedBorderColor: Color = Purple500,
    focusedLabelColor: Color = Purple500,
) = TextFieldDefaults.outlinedTextFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    focusedBorderColor = focusedBorderColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    focusedLabelColor = focusedLabelColor,
)