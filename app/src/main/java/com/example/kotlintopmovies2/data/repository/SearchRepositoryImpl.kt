package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: ApiServices) : SearchRepository {

    override suspend fun searchMovies(name: String): Flow<Response<ResponseMoviesList>> {
        return flow {emit(api.searchMovies(name))}.flowOn(Dispatchers.IO)
    }
}