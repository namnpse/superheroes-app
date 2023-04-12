package com.namnp.heroes.domain.use_cases

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetBannersUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Hero> {
        return repository.getBanners()
    }
}