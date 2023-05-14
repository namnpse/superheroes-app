package com.namnp.heroes.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.domain.repository.UserProfileResponse
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    val currentUser = repo.currentUser
    val firestore = Firebase.firestore
    private val _user: MutableStateFlow<UserProfileResponse> = MutableStateFlow(Response.Success(data = null))
    val user: StateFlow<UserProfileResponse> = _user

    fun getUser() {
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = Response.Loading
            _user.value = getUserFromFirestore().stateIn(viewModelScope).value
        }
    }

    private fun getUserFromFirestore() = callbackFlow<UserProfileResponse> {

        val collection = firestore
            .collection("users")
            .document(currentUser?.uid ?: "")
//            .document("WoOTsqL1rtRy0USwTY0mks6DPjJ3")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
                val user = value?.toObject(User::class.java)
//                println("RES: ${user?.email}")
                println("RES: ${value.toString()}")
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

    fun logOut() {
        repo.signOut()
    }
}