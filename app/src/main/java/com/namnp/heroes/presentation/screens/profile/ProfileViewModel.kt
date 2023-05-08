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
//    val getAllHeroes = useCases.getAllHeroesUseCase("Boruto")
//    val getMarvelHeroes = useCases.getAllHeroesUseCase("Marvel")
//    val getBannersUseCase = useCases.getBannersUseCase
    val currentUser = repo.currentUser
    private val firestore = FirebaseFirestore.getInstance()
    private val _banners = MutableStateFlow<List<Hero>>(emptyList())
    val banners = _banners
    val flowViaChannel = Channel<List<Hero>>()

//    init {
//        getBanners()
//    }
//
//    private fun getBanners() {
//        viewModelScope.launch {
////            _banners.value = getBannersUseCase()
//            getBookDetails().collectLatest {
//                println("DONE: $it")
//            }
//        }
//    }

    fun logOut() {
        repo.signOut()
    }
}