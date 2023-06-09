package com.namnp.heroes.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.DataStoreOperations
import com.namnp.heroes.domain.repository.LocalDataSource
import com.namnp.heroes.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
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

    suspend fun getBanners(): List<Hero> {
        return  remote.getBanners()
    }

    suspend fun likeHero(heroId: Int, isLiked: Boolean) {
        local.likeHero(heroId = heroId, isLiked = isLiked)
    }

    suspend fun getFavoriteHeroes() : LiveData<List<Hero?>>? {
        return local.getFavoriteHeroes()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    suspend fun saveUserInfo(user: User) {
        dataStore.saveUserInfo(user)
    }

    fun readUserInfo(): Flow<User> {
        return dataStore.readUserInfo()
    }

    fun getDataStoreValueByKey(key: String): Flow<String> {
        return dataStore.getDataStoreValueByKey(key)
    }

    suspend fun setDataStoreValueByKey(key: String?, value: String?) {
        dataStore.setDataStoreValueByKey(key, value)
    }

    suspend fun clearUserInfo() = dataStore.clearUserInfo()

}