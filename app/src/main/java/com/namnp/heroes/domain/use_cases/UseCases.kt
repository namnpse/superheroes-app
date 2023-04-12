package com.namnp.heroes.domain.use_cases

import com.namnp.heroes.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.namnp.heroes.domain.use_cases.get_all_heroes.GetMarvelHeroesUseCase
import com.namnp.heroes.domain.use_cases.get_detail_hero.GetDetailsHeroUseCase
import com.namnp.heroes.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.namnp.heroes.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.namnp.heroes.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val getMarvelHeroesUseCase: GetMarvelHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getDetailsHeroUseCase: GetDetailsHeroUseCase,
    val getBannersUseCase: GetBannersUseCase,
)