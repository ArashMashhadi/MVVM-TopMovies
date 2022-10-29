package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesEntity

interface FavoriteRepository {
    fun allFavoriteList () : MutableList<MoviesEntity>
}