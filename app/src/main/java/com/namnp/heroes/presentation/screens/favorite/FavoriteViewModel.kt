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
import com.namnp.heroes.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {
    private val currentUser = repo.currentUser
    // 2 way to init Firestore
    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDb = Firebase.firestore
    private var cachedFavoriteHeroes: List<Hero?> = emptyList()
    private val _favoriteHeroes: MutableStateFlow<ListHeroesResponse> = MutableStateFlow(Response.Success(data = null))
    val favoriteHeroes: StateFlow<ListHeroesResponse> = _favoriteHeroes

    init {
        getFavoriteHeroes()
        getRemoteFavoriteHeroes()
    }

    private fun getFavoriteHeroes() { // LOCAL
        if(currentUser == null) return
        viewModelScope.launch {
            _favoriteHeroes.value = Response.Loading
            useCases.getListFavoriteHeroesUseCase()?.observeForever {
                it?.let {
                    _favoriteHeroes.value = Response.Success(data = it)
                    cachedFavoriteHeroes = it
                    println("getListFavoriteHeroesUseCase ${it.size}")
                }
            }
        }

    }

    fun isLikedHero(hero: Hero?) : Boolean {
        if(currentUser == null || hero == null) return false
        if(_favoriteHeroes.value is Response.Success<List<Hero?>>) {
            val favoriteHeroes = _favoriteHeroes.value as Response.Success<List<Hero?>>
            return favoriteHeroes.data?.find { e -> hero.id == e?.id } != null
        }
        return false
    }

    fun likeHero(hero: Hero?, isLike: Boolean) {
        if(hero == null)    return
        println("LIKE HERO: ${hero.name} ${hero.id} $isLike")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.likeHeroUseCase(heroId = hero.id, isLiked = isLike)
        }
    }

    fun clearListFavoriteHeroes() {
        cachedFavoriteHeroes.forEach { hero ->
            likeHero(hero, false)
        }
    }

    private fun getRemoteFavoriteHeroes() { // REMOTE
        if(currentUser == null) return
        viewModelScope.launch(Dispatchers.IO) {
            val isAlreadyPullRemoteFavoriteHeroes = useCases.getDataStoreValueUseCase(
                    key = Constants.PREFERENCES_KEY_ALREADY_PULL_REMOTE_FAVORITE_HEROES
                ).stateIn(viewModelScope).value
            if(isAlreadyPullRemoteFavoriteHeroes == "true")   return@launch

//            val res = listenFavoriteHeroesFlow().stateIn(viewModelScope).value
            listenFavoriteHeroesFlow().collect { res ->
                println("COLLECT")
                _favoriteHeroes.value = res
                if(res is Response.Success) {
                    saveRemoteFavoriteHeroesToLocal(res.data)
                }
            }
        }
    }

    private fun saveRemoteFavoriteHeroesToLocal(heroes: List<Hero?>?) {
        if(heroes.isNullOrEmpty())  return
        viewModelScope.launch(Dispatchers.IO) {
            useCases.setDataStoreValueUseCase(
                key = Constants.PREFERENCES_KEY_ALREADY_PULL_REMOTE_FAVORITE_HEROES,
                value = "true"
            )
            heroes.forEach { hero ->
                likeHero(hero = hero, isLike = true)
            }
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
            println("snapshotListener remove")
            snapshotListener.remove()
        }
    }

//    fun likeHero(hero: Hero?, isLike: Boolean) {
//        if(hero == null)    return
//        println("LIKE HERO: ${hero.name} ${hero.id} $isLike")
//        if(isLike) {
//            firestoreDb
//                .collection("favorite")
//                .document(currentUser?.uid ?: "")
//                .collection("likes")
//                .document(hero.id.toString())
//                .set(hero)
////                .addOnCompleteListener {
////                    println("UPLOAD DONE")
////                }.addOnFailureListener {
////                    println("UPLOAD ERROR: ${it.message}")
////                }
//        }else {
//            firestoreDb
//                .collection("favorite")
//                .document(currentUser?.uid ?: "")
//                .collection("likes")
//                .document(hero.id.toString()).delete()
//        }
//    }
}