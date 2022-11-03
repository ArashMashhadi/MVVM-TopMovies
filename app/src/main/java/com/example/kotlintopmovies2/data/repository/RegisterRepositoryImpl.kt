package com.example.kotlintopmovies2.data.repository

import com.example.kotlintopmovies2.data.api.ApiServices
import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.data.model.register.ResponseRegister
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val api: ApiServices) :
    RegisterRepository {

    override suspend fun registerUser(body: BodyRegister): Flow<Response<ResponseRegister>> {
        return flow {emit(api.registerUser(body))}.flowOn(Dispatchers.IO)
    }
}