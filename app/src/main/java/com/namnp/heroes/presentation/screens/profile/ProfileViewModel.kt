package com.namnp.heroes.presentation.screens.profile

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.domain.repository.UserProfileResponse
import com.namnp.heroes.domain.use_cases.UseCases
import com.namnp.heroes.ui.theme.Theme
import com.namnp.heroes.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    val currentUser = repo.currentUser
    private val firestore = Firebase.firestore
    private val _user: MutableStateFlow<UserProfileResponse> = MutableStateFlow(Response.Success(data = null))
    val user: StateFlow<UserProfileResponse> = _user

    init {
        getUser()
        getRemoteUserInfo()
    }

    private fun getRemoteUserInfo() {
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            val isAlreadyPullRemoteUserInfo = useCases.getDataStoreValueUseCase(key = Constants.PREFERENCES_KEY_ALREADY_PULL_REMOTE_USER_INFO)
                .stateIn(viewModelScope).value
            if(isAlreadyPullRemoteUserInfo == "true")   return@launch
            _user.value = getUserFromFirestore().stateIn(viewModelScope).value
        }
    }

    fun getUser() {
        println("getUser")
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = Response.Loading
            val localUser = useCases.readUserInfoUseCase().stateIn(viewModelScope).value
            _user.value = Response.Success(data = localUser)
        }
    }

    private fun getUserFromFirestore() = callbackFlow<UserProfileResponse> {
        println("getUserFromFirestore")
        val collection = firestore
            .collection("users")
            .document(currentUser?.uid ?: "")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
                val user = value?.toObject(User::class.java)
                println("RES: ${value.toString()}")
                saveRemoteUserToLocal(user)
                Response.Success(data = user)
            } else {
                println("ERROR: $error")
                Response.Failure(error = error)
            }
            trySend(response)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }
    private fun saveRemoteUserToLocal(user: User?) {
        if(user == null)   return
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveUserInfoUseCase(user)
            useCases.setDataStoreValueUseCase(
                key = Constants.PREFERENCES_KEY_ALREADY_PULL_REMOTE_USER_INFO,
                value = "true"
            )
        }
    }

    fun changeTheme(theme: Theme) {
        val isDarkTheme = theme == Theme.Dark
        viewModelScope.launch(Dispatchers.IO) {
            useCases.setDataStoreValueUseCase(
                key = Constants.PREFERENCES_KEY_DARK_THEME,
                value = isDarkTheme.toString()
            )
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.clearUserInfoUseCase()
        }
        repo.signOut()
    }
}