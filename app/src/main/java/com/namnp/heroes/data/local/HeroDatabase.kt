package com.namnp.heroes.data.local

import androidx.room.Database
import com.namnp.heroes.data.local.dao.HeroDao
import com.namnp.heroes.data.local.dao.HeroRemoteKeyDao
import com.namnp.heroes.domain.model.Hero

@Database(entities = [Hero::class], version = 1)
abstract class HeroDatabase {

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeyDao() : HeroRemoteKeyDao
}