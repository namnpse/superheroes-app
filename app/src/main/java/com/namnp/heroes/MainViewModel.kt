package com.namnp.heroes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.auth.FirebaseUser
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.ui.theme.Theme
import com.namnp.heroes.ui.theme.ThemeState
import com.namnp.heroes.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

//import com.google.firebase.auth.ktx.auth

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {

    private val _themeState: MutableStateFlow<ThemeState> = MutableStateFlow(ThemeState(theme = Theme.Light))
    val themeState: StateFlow<ThemeState> = _themeState

    init {
        getTheme()
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    private fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            val isDarkTheme = useCases.getDataStoreValueUseCase(
                key = Constants.PREFERENCES_KEY_DARK_THEME
            ).stateIn(viewModelScope).value
            _themeState.value = ThemeState(theme = if(isDarkTheme == "true") Theme.Dark else Theme.Light)
        }
    }

//    private lateinit var auth: FirebaseAuth
//
//    init {
//        auth = FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }
//
//    private fun reload() {
//
//    }
//
//    fun signUp(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("TAG", "createUserWithEmail:success")
//                    val user = auth.currentUser
////                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
////                    Toast.makeText(
////                        baseContext,
////                        "Authentication failed.",
////                        Toast.LENGTH_SHORT,
////                    ).show()
////                    updateUI(nulll)
//                }
//            }
//    }
}