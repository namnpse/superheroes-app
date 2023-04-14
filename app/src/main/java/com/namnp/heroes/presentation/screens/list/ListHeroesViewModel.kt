package com.namnp.heroes.presentation.screens.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.namnp.heroes.domain.use_cases.UseCases
import com.namnp.heroes.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListHeroesViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val categoryId = savedStateHandle.get<String>(Constants.CATEGORY_ARGUMENT_KEY)
    val getAllHeroesByCategory = if(categoryId == "boruto") useCases.getAllHeroesUseCase() else useCases.getMarvelHeroesUseCase()
}