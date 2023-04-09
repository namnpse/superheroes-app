package com.namnp.heroes.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.namnp.heroes.data.local.toHero
import com.namnp.heroes.data.remote.HeroApi
import com.namnp.heroes.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val heroApi: HeroApi,
    private val query: String,
) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val response = heroApi.searchHeroes(name = query)
            val heroes = response.data.map { it.toHero() }
            if(heroes.isNotEmpty()) {
              LoadResult.Page(
                  data = heroes,
                  prevKey = response.prevPage,
                  nextKey = response.nextPage
              )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}