package com.namnp.heroes.domain.use_cases

import com.namnp.heroes.domain.use_cases.common.GetDataStoreValueUseCase
import com.namnp.heroes.domain.use_cases.common.SetDataStoreValueUseCase
import com.namnp.heroes.domain.use_cases.favorite.GetListFavoriteHeroesUseCase
import com.namnp.heroes.domain.use_cases.favorite.LikeHeroUseCase
import com.namnp.heroes.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.namnp.heroes.domain.use_cases.get_all_heroes.GetBannersUseCase
import com.namnp.heroes.domain.use_cases.get_all_heroes.GetMarvelHeroesUseCase
import com.namnp.heroes.domain.use_cases.get_detail_hero.GetDetailsHeroUseCase
import com.namnp.heroes.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.namnp.heroes.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.namnp.heroes.domain.use_cases.search_heroes.SearchHeroesUseCase
import com.namnp.heroes.domain.use_cases.user_info.ClearUserInfoUseCase
import com.namnp.heroes.domain.use_cases.user_info.ReadUserInfoUseCase
import com.namnp.heroes.domain.use_cases.user_info.SaveUserInfoUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val getMarvelHeroesUseCase: GetMarvelHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getDetailsHeroUseCase: GetDetailsHeroUseCase,
    val getBannersUseCase: GetBannersUseCase,
    val saveUserInfoUseCase: SaveUserInfoUseCase,
    val readUserInfoUseCase: ReadUserInfoUseCase,
    val clearUserInfoUseCase: ClearUserInfoUseCase,
    val likeHeroUseCase: LikeHeroUseCase,
    val getListFavoriteHeroesUseCase: GetListFavoriteHeroesUseCase,
    val setDataStoreValueUseCase: SetDataStoreValueUseCase,
    val getDataStoreValueUseCase: GetDataStoreValueUseCase,
)