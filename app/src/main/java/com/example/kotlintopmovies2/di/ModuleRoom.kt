package com.example.kotlintopmovies2.di

import android.content.Context
import androidx.room.Room
import com.example.kotlintopmovies2.db.MoviesDataBase
import com.example.kotlintopmovies2.db.MoviesEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRoom {

    private const val MOVIES_DATABASE ="movies_database"
    const val MOVIES_TABLE = "movies_table"

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MoviesDataBase::class.java, MOVIES_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun ProvideDao(db: MoviesDataBase) = db.moviesDao()

    @Provides
    @Singleton
    fun provideEntity() = MoviesEntity()
}