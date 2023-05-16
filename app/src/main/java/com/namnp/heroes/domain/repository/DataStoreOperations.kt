package com.namnp.heroes.domain.repository

import com.namnp.heroes.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveUserInfo(user: User)

    fun readUserInfo(): Flow<User>
}