package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import retrofit2.Response

interface HomeRepository {
    suspend fun topMoviesList (id : Int) : Response<ResponseMoviesList>
    suspend fun genresList () : Response<ResponseGenresList>
    suspend fun lastMoviesList () : Response<ResponseMoviesList>
}