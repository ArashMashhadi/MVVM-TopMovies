package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.db.MoviesDao
import com.example.kotlintopmovies2.db.MoviesEntity
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val dao : MoviesDao) : FavoriteRipository {
     /*fun allFavoriteList () = dao.getAllMovies()*/
     override fun allFavoriteList(): MutableList<MoviesEntity> {
          return dao.getAllMovies()
     }
}