package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.data.model.register.ResponseRegister
import com.example.kotlintopmovies2.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {
    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun sendRegisterUser(body: BodyRegister) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "register ${Thread.currentThread().name}")

        loading.postValue(true)

        repository.registerUser(body).also {
            if (it.isSuccessful) {
                registerUser.postValue(it.body())
            }
        }

        loading.postValue(false)
    }
}