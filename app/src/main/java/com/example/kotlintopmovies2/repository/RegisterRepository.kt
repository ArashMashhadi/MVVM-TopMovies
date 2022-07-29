package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.model.register.BodyRegister
import com.example.kotlintopmovies2.model.register.ResponseRegister
import retrofit2.Response

interface RegisterRepository {
    suspend fun registerUser(body : BodyRegister) : Response<ResponseRegister>

}