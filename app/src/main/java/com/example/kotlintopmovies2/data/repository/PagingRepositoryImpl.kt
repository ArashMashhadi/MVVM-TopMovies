package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(private val api: ApiServices) : PagingRepository {

    override suspend fun getAllMovies(page: Int): Response<ResponseMoviesList> {
        return api.getAllMovies(page)
    }
}