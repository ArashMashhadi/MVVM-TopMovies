package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import retrofit2.Response

interface SearchRepository {
    suspend fun searchMovies(name: String): Response<ResponseMoviesList>
}