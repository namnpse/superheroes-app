package com.namnp.heroes.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.namnp.heroes.data.local.HeroDatabase
import com.namnp.heroes.data.paging_source.HeroRemoteMediator
import com.namnp.heroes.data.paging_source.SearchHeroesSource
import com.namnp.heroes.data.remote.HeroApi
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.repository.RemoteDataSource
import com.namnp.heroes.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
) : RemoteDataSource {

    private val heroDao = heroDatabase.heroDao()

    override fun getAllHeroes(collection: String): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase,
                collection = collection,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE,),
            pagingSourceFactory = {
                SearchHeroesSource(heroApi = heroApi, query = query)
            }
        ).flow
    }
}