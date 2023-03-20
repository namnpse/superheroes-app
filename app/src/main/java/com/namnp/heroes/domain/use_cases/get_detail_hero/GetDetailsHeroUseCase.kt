package com.namnp.heroes.domain.use_cases.get_detail_hero

import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero

class GetDetailsHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getHeroByHeroId(heroId = heroId)
    }
}