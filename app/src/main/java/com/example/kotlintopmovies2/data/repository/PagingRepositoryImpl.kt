package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(private val api: ApiServices) : PagingRepository {

    override suspend fun getAllMovies(page: Int): Flow<Response<ResponseMoviesList>> {
        return flow {emit(api.getAllMovies(page))}.flowOn(Dispatchers.IO)
    }
}