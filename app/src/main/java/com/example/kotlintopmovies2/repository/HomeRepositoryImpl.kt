package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.api.ApiServices
import com.example.kotlintopmovies2.model.home.ResponseGenresList
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api : ApiServices) : HomeRepository {
    /*    suspend fun topMoviesList (id : Int) = api.moviesTopList(id)
    suspend fun genresList () = api.genresList()
    suspend fun lastMoviesList () = api.moviesLastList()*/
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