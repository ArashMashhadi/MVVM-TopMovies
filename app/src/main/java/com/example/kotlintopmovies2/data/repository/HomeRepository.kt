package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HomeRepository {
    suspend fun topMoviesList(id: Int): Flow<Response<ResponseMoviesList>>
    suspend fun genresList(): Flow<Response<ResponseGenresList>>
    suspend fun lastMoviesList(): Flow<Response<ResponseMoviesList>>
}