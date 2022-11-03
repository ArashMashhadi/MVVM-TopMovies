package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun allFavoriteList(): Flow<MutableList<MoviesEntity>>
}