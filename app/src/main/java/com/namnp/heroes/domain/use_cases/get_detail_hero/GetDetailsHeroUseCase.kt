package com.namnp.heroes.domain.use_cases.get_detail_hero

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetDetailsHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Flow<Hero?> {
        return repository.getHeroByHeroId(heroId = heroId)
    }
}