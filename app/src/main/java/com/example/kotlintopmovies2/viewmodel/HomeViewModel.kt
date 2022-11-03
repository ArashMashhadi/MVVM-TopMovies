package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.data.model.home.ResponseGenresList
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _topMoviesList = MutableLiveData<ResponseMoviesList>()
    val topMoviesList get() = _topMoviesList
    private val _genresList = MutableLiveData<ResponseGenresList>()
    val genresList get() = _genresList
    private val _lastMoviesList = MutableLiveData<ResponseMoviesList>()
    val lastMoviesList get() = _lastMoviesList
    val loading = MutableLiveData<Boolean>()

    fun loadTopMoviesList(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home ${Thread.currentThread().name}")
        val response = repository.topMoviesList(id)
        response.collect {
            if (it.isSuccessful) {
                _topMoviesList.postValue(it.body())
            }
        }
    }

    fun loadGenresList() = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home1 ${Thread.currentThread().name}")
        val response = repository.genresList()
        response.collect {
            if (it.isSuccessful) {
                _genresList.postValue(it.body())
            }
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "home2 ${Thread.currentThread().name}")
        loading.postValue(true)
        val response = repository.lastMoviesList()
        response.collect {
            if (it.isSuccessful) {
                _lastMoviesList.postValue(it.body())
            }
            loading.postValue(false)
        }
    }
}