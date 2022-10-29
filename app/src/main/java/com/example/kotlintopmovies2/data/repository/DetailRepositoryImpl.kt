package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.db.MoviesDao
import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.detail.ResponseDetail
import retrofit2.Response

import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val api : ApiServices, private val dao : MoviesDao) : DetailRepository {

    override suspend fun detailMovies(id: Int): Response<ResponseDetail> {
        return api.detailMovies(id)
    }

    override suspend fun insertMovies(entity: MoviesEntity) {
        return dao.insertMovies(entity)
    }

    override suspend fun deleteMovies(entity: MoviesEntity) {
        return dao.deleteMovies(entity)
    }

    override suspend fun existsMovies(id: Int) : Boolean{
        return dao.existsMovies(id)
    }


}