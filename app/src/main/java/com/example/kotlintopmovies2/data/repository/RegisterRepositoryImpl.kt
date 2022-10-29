package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.data.model.register.ResponseRegister
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl@Inject constructor(private val api: ApiServices) : RegisterRepository {

    override suspend fun registerUser(body: BodyRegister): Response<ResponseRegister> {
        return api.registerUser(body)
    }

}