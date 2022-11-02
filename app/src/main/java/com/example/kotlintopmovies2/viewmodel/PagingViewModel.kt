package com.example.kotlintopmovies2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kotlintopmovies2.data.repository.PagingRepository
import com.example.kotlintopmovies2.ui.paging.adapterPaging.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val repository: PagingRepository) : ViewModel() {

    val moviesLit = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}
