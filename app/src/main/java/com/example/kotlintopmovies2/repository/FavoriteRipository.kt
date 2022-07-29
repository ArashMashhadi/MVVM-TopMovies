package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.db.MoviesEntity

interface FavoriteRipository {
    fun allFavoriteList () : MutableList<MoviesEntity>
}