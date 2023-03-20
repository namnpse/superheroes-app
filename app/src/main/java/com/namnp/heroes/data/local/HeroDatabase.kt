package com.namnp.heroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.namnp.heroes.data.local.dao.HeroDao
import com.namnp.heroes.data.local.dao.HeroRemoteKeysDao
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeysDao() : HeroRemoteKeysDao
}