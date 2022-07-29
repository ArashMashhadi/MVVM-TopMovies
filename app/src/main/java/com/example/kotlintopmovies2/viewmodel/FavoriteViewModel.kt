package com.example.kotlintopmovies2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlintopmovies2.db.MoviesEntity
import com.example.kotlintopmovies2.repository.FavoriteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository : FavoriteRepositoryImpl) : ViewModel() {

    val favoriteList = MutableLiveData<MutableList<MoviesEntity>>()
    val empty = MutableLiveData<Boolean>()


    fun loadFavoriteList() = viewModelScope.launch(Dispatchers.IO) {
        Log.i("aaa", "favorit ${Thread.currentThread().name}")

        val list = repository.allFavoriteList()
        if(list.isNotEmpty()){
                favoriteList.postValue(list)
                empty.postValue(false)
            }else{
                empty.postValue(true)
        }
    }
}