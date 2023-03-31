package com.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.namnp.heroes.presentation.constants.WidgetIdentifier
import com.namnp.heroes.presentation.screens.search.SearchWidget
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_TEXT_FIELD)
            .performTextInput("Namnpse")
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_TEXT_FIELD)
            .assertTextEquals("Namnpse")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_TEXT_FIELD)
            .performTextInput("Namnpse")
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_TEXT_FIELD)
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_TEXT_FIELD)
            .performTextInput("Namnpse")
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET)
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET_CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(WidgetIdentifier.SEARCH_WIDGET)
            .assertDoesNotExist()
    }

}