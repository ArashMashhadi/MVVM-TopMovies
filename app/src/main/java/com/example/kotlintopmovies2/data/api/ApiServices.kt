package com.example.kotlintopmovies2.data.api

import com.example.kotlintopmovies2.data.model.detail.ResponseDetail
import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.data.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*


interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesList>

    //paging
    @GET("movies")
    suspend fun getAllMovies(@Query("page") page: Int): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovies(@Query("q") name: String): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun detailMovies(@Path("movie_id") id: Int): Response<ResponseDetail>
}