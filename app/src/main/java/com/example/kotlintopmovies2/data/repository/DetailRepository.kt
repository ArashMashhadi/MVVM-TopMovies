package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.data.model.detail.ResponseDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DetailRepository {
    //Api
    suspend fun detailMovies(id: Int): Flow<Response<ResponseDetail>>

    //DataBase
    suspend fun insertMovies(entity: MoviesEntity)
    suspend fun deleteMovies(entity: MoviesEntity)
    suspend fun existsMovies(id: Int): Boolean
}