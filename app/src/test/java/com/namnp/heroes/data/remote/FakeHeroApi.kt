package com.namnp.heroes.data.remote

import com.namnp.heroes.domain.ApiResponse
import com.namnp.heroes.domain.HeroResponse
import com.namnp.heroes.domain.model.Hero


class FakeBorutoApi : HeroApi {

    private val heroes = listOf(
        Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 2,
            name = "Naruto",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 3,
            name = "Sakura",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        )
    )

    override suspend fun getAllHeroes(page: Int, collection: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun getMarvelHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        val searchedHeroes = findHeroes(name = name)
        return ApiResponse(
            success = true,
            message = "ok",
            data = searchedHeroes.map { it.toHeroDto() }
        )
    }

    override suspend fun getHeroById(id: Int): HeroResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getBanners(): ApiResponse {
        TODO("Not yet implemented")
    }

    private fun findHeroes(name: String): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(name.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}