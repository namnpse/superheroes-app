package com.namnp.heroes.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHero(query = query)
    }
}