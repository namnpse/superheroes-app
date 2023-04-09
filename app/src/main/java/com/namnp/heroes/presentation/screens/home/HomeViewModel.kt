package com.namnp.heroes.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()
    val getMarvelHeroes = useCases.getMarvelHeroesUseCase()
}