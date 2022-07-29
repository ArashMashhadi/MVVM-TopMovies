package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.repository.SearchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepositoryImpl) : ViewModel() {

    val moviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()


    fun loadSearchMovise(name: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "search ${Thread.currentThread().name}")

        loading.postValue(true)
        val response = repository.searchMovies(name)
        if(response.isSuccessful){
            if(response.body()?.data!!.isNotEmpty()){
                moviesList.postValue(response.body())
                empty.postValue(false)
            }else{
                empty.postValue(true)
            }
        }
        loading.postValue(false)
    }
}
