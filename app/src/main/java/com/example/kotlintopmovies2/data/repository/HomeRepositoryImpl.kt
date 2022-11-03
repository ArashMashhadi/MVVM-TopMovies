package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: ApiServices) : HomeRepository {

    override suspend fun topMoviesList(id: Int): Flow<Response<ResponseMoviesList>> {
        return flow {emit(api.moviesTopList(id))}.flowOn(Dispatchers.IO)
    }

    override suspend fun genresList(): Flow<Response<ResponseGenresList>> {
        return flow {emit(api.genresList())}.flowOn(Dispatchers.IO)
    }

    override suspend fun lastMoviesList(): Flow<Response<ResponseMoviesList>> {
        return flow {emit(api.moviesLastList())}.flowOn(Dispatchers.IO)
    }
}