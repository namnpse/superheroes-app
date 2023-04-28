package com.namnp.heroes.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
//    val getAllHeroes = useCases.getAllHeroesUseCase("Boruto")
//    val getMarvelHeroes = useCases.getAllHeroesUseCase("Marvel")
//    val getBannersUseCase = useCases.getBannersUseCase
    private val firestore = FirebaseFirestore.getInstance()
    private val _banners = MutableStateFlow<List<Hero>>(emptyList())
    val banners = _banners

    init {
        getBanners()
    }

    private fun getBanners() {
        viewModelScope.launch {
//            _banners.value = getBannersUseCase()
            getBookDetails().collectLatest {
                println("DONE: $it")
            }
        }
    }


    private fun getBookDetails() = callbackFlow<String> {

        val collection = firestore.collection("posts")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
//                OnSuccess(value)
                println("RES: ${value?.documents?.size ?: "NULL"}")
            } else {
//                OnError(error)
                println("ERROR: ${error}")
            }

            trySend(response.toString())
        }

        awaitClose {
            snapshotListener.remove()
        }
    }
}