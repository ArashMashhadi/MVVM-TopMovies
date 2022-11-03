package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository : FavoriteRepository) : ViewModel() {

    private val _favoriteList = MutableLiveData<MutableList<MoviesEntity>>()
    val favoriteList get() = _favoriteList
    val empty = MutableLiveData<Boolean>()

    fun loadFavoriteList() = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "favorite ${Thread.currentThread().name}")
        val list = repository.allFavoriteList()
        list.collect {
            if (it.isNotEmpty()) {
                _favoriteList.postValue(it)
                empty.postValue(false)
            } else {
                empty.postValue(true)
            }
        }
    }
}