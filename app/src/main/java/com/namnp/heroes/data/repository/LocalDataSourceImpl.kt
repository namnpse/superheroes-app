package com.namnp.heroes.data.repository

import com.namnp.heroes.data.local.HeroDatabase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.LocalDataSource

class LocalDataSourceImpl(heroDatabase: HeroDatabase): LocalDataSource {

    private val heroDao = heroDatabase.heroDao()

    override suspend fun getHeroByHeroId(heroId: Int): Hero {
        return heroDao.getHeroByHeroId(heroId = heroId)
    }
}