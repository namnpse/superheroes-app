package com.namnp.heroes.data.remote

import com.namnp.heroes.domain.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {

    @GET("/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/marvel/heroes")
    suspend fun getMarvelHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse

}