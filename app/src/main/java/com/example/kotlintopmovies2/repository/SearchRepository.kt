package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import retrofit2.Response

interface SearchRepository {
    suspend fun searchMovies (name : String) : Response<ResponseMoviesList>

}