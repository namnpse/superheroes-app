package com.namnp.heroes.presentation.screens.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.domain.repository.SaveUserResponse
import com.namnp.heroes.domain.repository.SignUpResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(data = null))
        private set

    var saveUserResponse by mutableStateOf<SaveUserResponse>(Response.Success(data = false))
        private set

    var isSignUpSuccess = false

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun saveUserToFirestore(user: User) {
        isSignUpSuccess = true
        repo.signOut()
        saveUserResponse = Response.Loading
        val db = Firebase.firestore
        val userMap = hashMapOf(
            "userId" to (user.id ?: ""),
            "nickName" to (user.nickName ?: ""),
            "email" to (user.email ?: ""),
            "phoneNumber" to (user.phone ?: ""),
            "photoUrl" to (user.photoUrl ?: ""),
            "bio" to (user.bio ?: ""),
        )
        db.collection("users").document(user.id ?: "")
            .set(userMap)
            .addOnSuccessListener {
                println("DocumentSnapshot successfully written!")
                saveUserResponse = Response.Success(data = true)
            }
            .addOnFailureListener { e ->
                println("Error writing document ${e.message}")
                saveUserResponse = Response.Failure(error = e)
            }
    }
}