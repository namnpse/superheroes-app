package com.namnp.heroes.domain.repository

import com.namnp.heroes.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveUserInfo(user: User)

    fun readUserInfo(): Flow<User>

    fun getDataStoreValueByKey(key: String): Flow<String>

    suspend fun setDataStoreValueByKey(key: String?, value: String?)

    suspend fun clearUserInfo()
}