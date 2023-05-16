package com.namnp.heroes.domain.use_cases.user_info

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.User
import kotlinx.coroutines.flow.Flow

class ReadUserInfoUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<User> {
        return repository.readUserInfo()
    }
}