package com.raul.axpe_prueba_tecnica.di

import com.raul.axpe_prueba_tecnica.data.RandomUserRepository
import com.raul.axpe_prueba_tecnica.data.RandomUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRandomUserRepository(
        randomUserRepositoryImpl: RandomUserRepositoryImpl
    ) : RandomUserRepository
}