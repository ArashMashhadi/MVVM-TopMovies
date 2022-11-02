package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: ApiServices) : SearchRepository {

    override suspend fun searchMovies(name: String): Response<ResponseMoviesList> {
        return api.searchMovies(name)
    }
}