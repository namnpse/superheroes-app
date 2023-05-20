package com.namnp.heroes.domain.use_cases

import com.namnp.heroes.data.repository.Repository

class LikeHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int, isLiked: Boolean) {
        return repository.likeHero(heroId, isLiked)
    }
}