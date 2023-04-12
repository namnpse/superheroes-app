package com.namnp.heroes.presentation.screens.list

import androidx.lifecycle.ViewModel
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListHeroesViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()
    val getMarvelHeroes = useCases.getMarvelHeroesUseCase()
}