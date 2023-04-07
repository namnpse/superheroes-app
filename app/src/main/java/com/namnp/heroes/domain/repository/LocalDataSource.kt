package com.namnp.heroes.domain.repository

import com.namnp.heroes.domain.model.Hero

interface LocalDataSource {
    suspend fun getHeroByHeroId(heroId: Int): Hero?
    suspend fun saveHero(hero: Hero)
}