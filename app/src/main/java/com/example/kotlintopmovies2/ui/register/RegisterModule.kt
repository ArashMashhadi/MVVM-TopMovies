package com.example.kotlintopmovies2.ui.register

import com.example.kotlintopmovies2.data.model.register.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {
    @Provides
    @Singleton
    fun provideUserBody()= BodyRegister()
}