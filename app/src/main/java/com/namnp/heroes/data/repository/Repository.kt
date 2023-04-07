package com.namnp.heroes.data.repository

import androidx.paging.PagingData
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.DataStoreOperations
import com.namnp.heroes.domain.repository.LocalDataSource
import com.namnp.heroes.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllHeroes(collection: String = ""): Flow<PagingData<Hero>> {
        return remote.getAllHeroes(collection)
    }

    fun searchHero(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }

    suspend fun getHeroByHeroId(heroId: Int): Flow<Hero?> {
        val localData = local.getHeroByHeroId(heroId = heroId)
        return remote.getHeroById(heroId)
            .onStart {
                if (localData != null)
                    emit(localData)
            }
            .onEach { remoteData ->
                if (remoteData != null) {
                    local.saveHero(remoteData)
                }
            }
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}