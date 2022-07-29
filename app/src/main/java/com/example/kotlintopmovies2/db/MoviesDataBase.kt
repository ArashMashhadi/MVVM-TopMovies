package com.example.kotlintopmovies2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MoviesEntity::class] , version = 2 , exportSchema = false)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao
}