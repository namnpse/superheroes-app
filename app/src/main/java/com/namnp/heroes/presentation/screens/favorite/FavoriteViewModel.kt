package com.namnp.heroes.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.domain.repository.ListHeroesResponse
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    val currentUser = repo.currentUser
    private val firestore = FirebaseFirestore.getInstance()
    private val _favoriteHeroes: MutableStateFlow<ListHeroesResponse> = MutableStateFlow(Response.Success(data = null))
    val favoriteHeroes: StateFlow<ListHeroesResponse> = _favoriteHeroes
    val firestoreDb = Firebase.firestore

    fun getFavoriteHeroes() {
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteHeroes.value = Response.Loading
            _favoriteHeroes.value = listenFavoriteHeroesFlow().stateIn(viewModelScope).value
        }
    }

    private fun listenFavoriteHeroesFlow() = callbackFlow<ListHeroesResponse> {

        val collection = firestore
            .collection("favorite")
            .document(currentUser?.uid ?: "")
            .collection("likes")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
                val size = value?.documents?.size
                println("RES: $size")
                val heroes = value?.documents?.map { doc -> doc.toObject(Hero::class.java) } ?: emptyList<Hero>()
                Response.Success(data = heroes)
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

    fun likeHero(hero: Hero?, isLike: Boolean) {
        if(hero == null)    return
        println("LIKE HERO: ${hero.name} ${hero.id} $isLike")
        if(isLike) {
            firestoreDb
                .collection("favorite")
                .document(currentUser?.uid ?: "")
                .collection("likes")
                .document(hero.id.toString())
                .set(hero)
//                .addOnCompleteListener {
//                    println("UPLOAD DONE")
//                }.addOnFailureListener {
//                    println("UPLOAD ERROR: ${it.message}")
//                }
        }else {
            firestoreDb
                .collection("favorite")
                .document(currentUser?.uid ?: "")
                .collection("likes")
                .document(hero.id.toString()).delete()
        }
    }
}