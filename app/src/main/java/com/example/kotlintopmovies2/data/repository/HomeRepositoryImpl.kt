package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: ApiServices) : HomeRepository {

    override suspend fun topMoviesList(id: Int): Response<ResponseMoviesList> {
        return api.moviesTopList(id)
    }

    override suspend fun genresList(): Response<ResponseGenresList> {
        return api.genresList()
    }

    override suspend fun lastMoviesList(): Response<ResponseMoviesList> {
        return api.moviesLastList()
    }
}