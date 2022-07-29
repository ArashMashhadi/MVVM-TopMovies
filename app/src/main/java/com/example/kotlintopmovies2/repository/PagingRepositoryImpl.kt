package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.api.ApiServices
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(private val api : ApiServices) : PagingRepository {
    /*
    suspend fun getAllMovies(page : Int) = api.getAllMovies(page)
*/
    override suspend fun getAllMovies(page: Int): Response<ResponseMoviesList> {
        return api.getAllMovies(page)
    }

}