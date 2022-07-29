package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.db.MoviesDao
import com.example.kotlintopmovies2.db.MoviesEntity
import com.example.kotlintopmovies2.api.ApiServices
import com.example.kotlintopmovies2.model.detail.ResponseDetail
import retrofit2.Response

import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val api : ApiServices,
                                               private val dao : MoviesDao) : DetailRepository {
    /*    //Api
    suspend fun detailMovies (id: Int) = api.detailMovies(id)
    //DataBase
    suspend fun insertMovies (entity: MoviesEntity) = dao.insertMovies(entity)
    suspend fun deleteMovies (entity: MoviesEntity) = dao.deleteMovies(entity)
    suspend fun existsMovies (id: Int) = dao.existsMovies(id)*/
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