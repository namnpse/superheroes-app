package com.namnp.heroes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namnp.heroes.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM HERO_TABLE ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM HERO_TABLE WHERE id=:heroId")
    fun getHeroByHeroId(heroId: Int): Hero?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHero(hero: Hero)

    @Query("DELETE FROM HERO_TABLE")
    suspend fun deleteAllHeroes()
}