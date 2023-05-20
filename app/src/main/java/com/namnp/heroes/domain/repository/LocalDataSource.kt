package com.namnp.heroes.domain.repository

import androidx.lifecycle.LiveData
import com.namnp.heroes.domain.model.Hero

interface LocalDataSource {
    suspend fun getHeroByHeroId(heroId: Int): Hero?
    suspend fun saveHero(hero: Hero)
    suspend fun likeHero(heroId: Int, isLiked: Boolean)
    suspend fun getFavoriteHeroes() : LiveData<List<Hero?>>?
}