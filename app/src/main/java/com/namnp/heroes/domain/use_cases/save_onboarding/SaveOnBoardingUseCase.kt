package com.namnp.heroes.domain.use_cases.save_onboarding

import com.namnp.heroes.data.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}