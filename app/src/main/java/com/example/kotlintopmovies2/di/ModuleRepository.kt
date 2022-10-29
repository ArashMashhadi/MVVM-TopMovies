package com.example.kotlintopmovies2.di

import com.example.kotlintopmovies2.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ModuleRepository {
    @Binds
    abstract fun moduleDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    abstract fun moduleFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    abstract fun moduleHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun modulePagingRepository(pagingRepositoryImpl: PagingRepositoryImpl): PagingRepository

    @Binds
    abstract fun moduleRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    abstract fun moduleSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}