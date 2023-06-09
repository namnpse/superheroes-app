package com.namnp.heroes.domain.repository

import androidx.paging.PagingData
import com.namnp.heroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(collection: String = ""): Flow<PagingData<Hero>>

    fun searchHeroes(query: String): Flow<PagingData<Hero>>

    fun getHeroById(id: Int): Flow<Hero?>

    suspend fun getBanners(): List<Hero>
}