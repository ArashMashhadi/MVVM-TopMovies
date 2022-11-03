package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesDao
import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.detail.ResponseDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val api: ApiServices,
    private val dao: MoviesDao,
) : DetailRepository {

    override suspend fun detailMovies(id: Int): Flow<Response<ResponseDetail>> {
        return flow {emit(api.detailMovies(id))}.flowOn(Dispatchers.IO)
    }

    override suspend fun insertMovies(entity: MoviesEntity) {
        return dao.insertMovies(entity)
    }

    override suspend fun deleteMovies(entity: MoviesEntity) {
        return dao.deleteMovies(entity)
    }

    override suspend fun existsMovies(id: Int): Boolean {
        return dao.existsMovies(id)
    }


}