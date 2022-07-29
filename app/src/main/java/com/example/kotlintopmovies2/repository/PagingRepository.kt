package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import retrofit2.Response

interface PagingRepository {
    suspend fun getAllMovies(page : Int) : Response<ResponseMoviesList>

}