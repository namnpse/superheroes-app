package com.namnp.heroes.domain.use_cases

import com.namnp.heroes.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.namnp.heroes.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.namnp.heroes.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)