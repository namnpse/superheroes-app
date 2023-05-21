package com.namnp.heroes.presentation.screens.update_profile

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.*
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    val storage = Firebase.storage
    val db = Firebase.firestore
    private val currentUser = repo.currentUser
    var updateUserInfoResponse by mutableStateOf<SaveUserResponse>(Response.Success(data = false))
        private set

    private val _avatarUrl: MutableStateFlow<String?> = MutableStateFlow(null)
    val avatarUrl: StateFlow<String?> = _avatarUrl.asStateFlow()

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    init {
        getUser()
    }

    private fun getUser() {
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            val userLocal = useCases.readUserInfoUseCase().stateIn(viewModelScope).value
            _user.value = userLocal
            _avatarUrl.value = userLocal.photoUrl
        }
    }

    fun uploadAvatarImage(file: Uri?) = viewModelScope.launch {
        if(file == null) return@launch
        val storageRef = storage.reference
        val timeStamp = System.currentTimeMillis()
        val avatarImageRef = storageRef.child("avatar/${currentUser?.uid ?: ""}_$timeStamp")
        val uploadTask = avatarImageRef.putFile(file)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
//                task.exception?.let {
//                    throw it
//                }
            }
            avatarImageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                updateUserInfo(user = User(photoUrl = downloadUri.toString()))
                _avatarUrl.value = downloadUri.toString()
                saveUserAvatarToFirestore(downloadUri.toString())
            } else {
                // Handle failures
            }
        }
    }

    fun updateUserInfo(nickName: String, phone: String, bio: String) {
        val user = User(
            id = currentUser?.uid ?: "",
            nickName = nickName,
            email = currentUser?.email ?: "",
            phone = phone,
            photoUrl = avatarUrl.value,
            bio = bio,
        )
        viewModelScope.launch {
            useCases.saveUserInfoUseCase(user)
        }
        saveUserToFirestore(user)
    }

    private fun saveUserToFirestore(user: User) {
        updateUserInfoResponse = Response.Loading
        val userMap: Map<String, String> = hashMapOf(
            "id" to (currentUser?.uid ?: ""),
            "email" to (currentUser?.email ?: ""),
            "nickName" to (user.nickName ?: ""),
            "phone" to (user.phone ?: ""),
            "bio" to (user.bio ?: ""),
        )
        db.collection("users")
            .document(currentUser?.uid ?: "")
            .update(userMap)
            .addOnSuccessListener {
                // save local
                updateUserInfo(user)
                updateUserInfoResponse = Response.Success(data = true)
            }
            .addOnFailureListener { e ->
                updateUserInfoResponse = Response.Failure(error = e)
            }
    }

    private fun saveUserAvatarToFirestore(photoUrl: String) {
        val userMap: Map<String, String> = hashMapOf(
            "photoUrl" to photoUrl,
        )
        db.collection("users")
            .document(currentUser?.uid ?: "")
            .update(userMap)
    }

    private fun updateUserInfo(user: User) {
        viewModelScope.launch {
            useCases.saveUserInfoUseCase(user)
        }
    }
}