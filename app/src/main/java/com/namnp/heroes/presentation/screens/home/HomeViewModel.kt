package com.namnp.heroes.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()
    val getMarvelHeroes = useCases.getMarvelHeroesUseCase()
    val getBannersUseCase = useCases.getBannersUseCase
    private val _banners = MutableStateFlow<List<Hero>>(emptyList())
    val banners = _banners

    init {
        getBanners()
    }

    fun getBanners() {
        viewModelScope.launch {
            _banners.value = getBannersUseCase()
        }
    }
}