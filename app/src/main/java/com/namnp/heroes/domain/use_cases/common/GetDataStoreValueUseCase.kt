package com.namnp.heroes.domain.use_cases.common

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetDataStoreValueUseCase(
    private val repository: Repository
) {
    operator fun invoke(key: String): Flow<String> {
        return repository.getDataStoreValueByKey(key)
    }
}