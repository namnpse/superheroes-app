package com.namnp.heroes.domain.use_cases.user_info
import com.namnp.heroes.data.repository.Repository

class ClearUserInfoUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.clearUserInfo()
    }
}