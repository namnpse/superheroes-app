package com.namnp.heroes.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Response<AuthResult>
typealias SaveUserResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>
typealias ListHeroesResponse = Response<List<Hero?>>

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}