package com.namnp.heroes.domain.use_cases.common

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.User
import kotlinx.coroutines.flow.Flow

class SetDataStoreValueUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(key: String?, value: String?) {
        return repository.setDataStoreValueByKey(key, value)
    }
}