package com.namnp.heroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.namnp.heroes.data.local.dao.HeroDao
import com.namnp.heroes.data.local.dao.HeroRemoteKeysDao
import com.namnp.heroes.domain.model.Hero
import com.namnp.heroes.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): HeroDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, HeroDatabase::class.java)
            } else {
                Room.databaseBuilder(context, HeroDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeysDao() : HeroRemoteKeysDao
}