package com.namnp.heroes.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.*
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    val currentUser = repo.currentUser

    fun logOut() {
        repo.signOut()
    }
}