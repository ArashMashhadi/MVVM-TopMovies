package com.example.kotlintopmovies2.ui.paging.adapterPaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.data.repository.PagingRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repository: PagingRepository) :
    PagingSource<Int, ResponseMoviesList.Data>() {

    override fun getRefreshKey(state: PagingState<Int, ResponseMoviesList.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMoviesList.Data> {
        val responseData = mutableListOf<ResponseMoviesList.Data>()
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllMovies(currentPage)
            response.collect {
                val data = it.body()?.data ?: emptyList()
                responseData.addAll(data)
            }
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}