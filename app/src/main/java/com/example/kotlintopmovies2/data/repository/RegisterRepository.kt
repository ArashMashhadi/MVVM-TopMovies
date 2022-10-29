package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.data.model.register.ResponseRegister
import retrofit2.Response

interface RegisterRepository {
    suspend fun registerUser(body : BodyRegister) : Response<ResponseRegister>

}