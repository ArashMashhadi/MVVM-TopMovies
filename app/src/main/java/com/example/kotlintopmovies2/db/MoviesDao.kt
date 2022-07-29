package com.example.kotlintopmovies2.db

import androidx.room.*
import com.example.kotlintopmovies2.di.ModuleRoom.MOVIES_TABLE

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies (entity: MoviesEntity)

    @Delete
    suspend fun deleteMovies (entity: MoviesEntity)

    @Query("SELECT * FROM $MOVIES_TABLE")
    fun getAllMovies() : MutableList<MoviesEntity>

    @Query("SELECT EXISTS (SELECt 1 FROM $MOVIES_TABLE WHERE id = :movieId)")
    suspend fun existsMovies(movieId : Int) : Boolean
}