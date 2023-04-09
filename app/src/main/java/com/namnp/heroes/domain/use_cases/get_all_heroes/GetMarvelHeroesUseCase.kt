package com.namnp.heroes.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetMarvelHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes("marvel")
    }
}