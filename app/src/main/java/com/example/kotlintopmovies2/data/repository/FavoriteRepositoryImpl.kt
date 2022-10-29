package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesDao
import com.example.kotlintopmovies2.data.db.MoviesEntity
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val dao : MoviesDao) : FavoriteRepository {
     override fun allFavoriteList(): MutableList<MoviesEntity> {
          return dao.getAllMovies()
     }
}