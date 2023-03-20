package com.namnp.heroes.di

import android.content.Context
import androidx.room.Room
import com.namnp.heroes.data.local.HeroDatabase
import com.namnp.heroes.data.repository.LocalDataSourceImpl
import com.namnp.heroes.domain.repository.LocalDataSource
import com.namnp.heroes.util.Constants.HERO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        HeroDatabase::class.java,
        HERO_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: HeroDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            heroDatabase = database
        )
    }
}