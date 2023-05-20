package com.namnp.heroes.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namnp.heroes.domain.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM HERO_TABLE WHERE collection=:collection ORDER BY id ASC")
    fun getAllHeroes(collection: String): PagingSource<Int, Hero>

    @Query("SELECT * FROM HERO_TABLE WHERE id=:heroId")
    fun getHeroByHeroId(heroId: Int): Hero?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHero(hero: Hero)

    @Query("DELETE FROM HERO_TABLE")
    suspend fun deleteAllHeroes()

    @Query("UPDATE HERO_TABLE SET is_liked=:isLike WHERE id=:heroId")
    suspend fun likeHero(heroId: Int, isLike: Boolean)

    // https://stackoverflow.com/questions/47730820/hardcode-boolean-query-in-room-database
    // SQLite does not have a boolean data type.
    // -> Room maps it to an INTEGER column, mapping true to 1 and false to 0
    @Query("SELECT * FROM HERO_TABLE WHERE is_liked = 1")
    fun getFavoriteHeroes(): LiveData<List<Hero?>>?

}