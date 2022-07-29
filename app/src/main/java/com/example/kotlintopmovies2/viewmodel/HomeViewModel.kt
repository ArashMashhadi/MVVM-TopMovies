package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.model.home.ResponseGenresList
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepositoryImpl) : ViewModel(){

    val topMoviesList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenresList>()
    val lastMoviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()



    fun loadTopMoviesList (id : Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home ${Thread.currentThread().name}")

        val response = repository.topMoviesList(id)
        if(response.isSuccessful){
            topMoviesList.postValue(response.body())
        }
    }

    fun loadGenresList ()= viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home1 ${Thread.currentThread().name}")

        val response = repository.genresList()
        if(response.isSuccessful){
            genresList.postValue(response.body())
        }
    }
    fun loadLastMoviesList () = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home2 ${Thread.currentThread().name}")

        loading.postValue(true)
        val response = repository.lastMoviesList()
        if(response.isSuccessful){
            lastMoviesList.postValue(response.body())
        }
        loading.postValue(false)
    }


}