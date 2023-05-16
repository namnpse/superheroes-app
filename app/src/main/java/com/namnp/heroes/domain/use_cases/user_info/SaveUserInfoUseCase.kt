package com.namnp.heroes.domain.use_cases.user_info
import com.namnp.heroes.data.repository.Repository
import com.namnp.heroes.domain.model.User

class SaveUserInfoUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUserInfo(user = user)
    }
}