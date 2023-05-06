package com.namnp.heroes.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.namnp.heroes.data.repository.AuthRepositoryImpl
import com.namnp.heroes.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )
}