package com.namnp.heroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.namnp.heroes.data.local.dao.HeroDao
import com.namnp.heroes.data.local.dao.HeroRemoteKeyDao
import com.namnp.heroes.domain.model.Hero

@Database(entities = [Hero::class, HeroRemoteKeyDao::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeyDao() : HeroRemoteKeyDao
}