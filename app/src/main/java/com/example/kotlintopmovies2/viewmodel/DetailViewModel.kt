package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.db.MoviesEntity
import com.example.kotlintopmovies2.model.detail.ResponseDetail
import com.example.kotlintopmovies2.repository.DetailRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepositoryImpl) :
    ViewModel() {

    //Api

    private val _detailMovie = MutableLiveData<ResponseDetail>()
    val detailMovie: LiveData<ResponseDetail>
        get() = _detailMovie


    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovies(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "detail ${Thread.currentThread().name}")

        loading.postValue(true)
        val response = repository.detailMovies(id)
        if (response.isSuccessful) {
            _detailMovie.postValue(response.body())
        }
        loading.postValue(false)
    }

    //DataBase
    val isFavorite = MutableLiveData<Boolean>()
    suspend fun existsMovies(id: Int) =
        withContext(viewModelScope.coroutineContext) { repository.existsMovies(id) }

    fun favoriteMovies(id: Int, entity: MoviesEntity) = viewModelScope.launch {
        Log.i("aaa", "detail1 ${Thread.currentThread().name}")

        val exists = repository.existsMovies(id)
        if (exists) {
            isFavorite.postValue(false)
            repository.deleteMovies(entity)
        } else {
            isFavorite.postValue(true)
            repository.insertMovies(entity)
        }
    }

}