package com.example.kotlintopmovies2.ui.paging.adapterPaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlintopmovies2.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.repository.PagingRepositoryImpl
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repository: PagingRepositoryImpl) :
    PagingSource<Int , ResponseMoviesList.Data> (){

    override fun getRefreshKey(state: PagingState<Int, ResponseMoviesList.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMoviesList.Data> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllMovies(currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<ResponseMoviesList.Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if(currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}