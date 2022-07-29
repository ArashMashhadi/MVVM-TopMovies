package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.api.ApiServices
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api : ApiServices) : SearchRepository {
    /*
    suspend fun searchMovies (name : String) = api.searchMovies(name)
*/
    override suspend fun searchMovies(name: String): Response<ResponseMoviesList> {
        return api.searchMovies(name)
    }
}