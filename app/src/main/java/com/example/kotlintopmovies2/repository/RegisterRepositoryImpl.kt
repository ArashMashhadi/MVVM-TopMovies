package com.example.kotlintopmovies2.repository

import com.example.kotlintopmovies2.api.ApiServices
import com.example.kotlintopmovies2.model.register.BodyRegister
import com.example.kotlintopmovies2.model.register.ResponseRegister
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl@Inject constructor(private val api:ApiServices) : RegisterRepository {
    /*
    suspend fun registerUser(body : BodyRegister) = api.registerUser(body)
*/
    override suspend fun registerUser(body: BodyRegister): Response<ResponseRegister> {
        return api.registerUser(body)
    }

}