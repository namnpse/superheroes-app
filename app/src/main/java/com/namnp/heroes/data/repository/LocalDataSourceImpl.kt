package com.namnp.heroes.data.repository

import androidx.lifecycle.LiveData
import com.namnp.heroes.data.local.HeroDatabase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.LocalDataSource

class LocalDataSourceImpl(heroDatabase: HeroDatabase): LocalDataSource {

    private val heroDao = heroDatabase.heroDao()

    override suspend fun getHeroByHeroId(heroId: Int): Hero? {
        return heroDao.getHeroByHeroId(heroId = heroId)
    }

    override suspend fun saveHero(hero: Hero) {
        heroDao.addHero(hero)
    }

    override suspend fun likeHero(heroId: Int, isLiked: Boolean) {
        heroDao.likeHero(heroId, isLiked)
    }

    override suspend fun getFavoriteHeroes(): LiveData<List<Hero?>>? {
        return heroDao.getFavoriteHeroes()
    }
}