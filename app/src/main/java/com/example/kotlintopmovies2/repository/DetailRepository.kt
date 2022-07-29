package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.db.MoviesEntity
import com.example.kotlintopmovies2.model.detail.ResponseDetail
import retrofit2.Response

interface DetailRepository {
    //Api
    suspend fun detailMovies (id: Int) : Response<ResponseDetail>

    //DataBase
    suspend fun insertMovies (entity: MoviesEntity)
    suspend fun deleteMovies (entity: MoviesEntity)
    suspend fun existsMovies (id: Int) : Boolean
}