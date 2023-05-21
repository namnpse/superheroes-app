package com.namnp.heroes.domain.use_cases.favorite

import androidx.lifecycle.LiveData
import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.Hero

class GetListFavoriteHeroesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() : LiveData<List<Hero?>>?  {
        return repository.getFavoriteHeroes()
    }
}