package com.namnp.heroes.data.repository

import androidx.paging.PagingData
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.DataStoreOperations
import com.namnp.heroes.domain.repository.LocalDataSource
import com.namnp.heroes.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHero(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }

    suspend fun getHeroByHeroId(heroId: Int): Hero {
        return local.getHeroByHeroId(heroId = heroId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}