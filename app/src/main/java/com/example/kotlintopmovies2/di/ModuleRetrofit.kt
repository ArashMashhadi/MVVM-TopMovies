package com.example.kotlintopmovies2.di

import com.example.kotlintopmovies2.data.api.ApiServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRetrofit {

    private const val BASE_URL = "https://moviesapi.ir/api/v1/"
    private const val TIME_OUT = 60L
    private const val BODY = "body"
    private const val HEADERS = "headers"


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Named(BODY)
    fun provideInterceptorBody(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Named(HEADERS)
    fun provideInterceptorHeader(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    fun provideClient(
        @Named(BODY) body: HttpLoggingInterceptor,
        @Named(HEADERS) header: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(body)
        .addInterceptor(header)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)


}

