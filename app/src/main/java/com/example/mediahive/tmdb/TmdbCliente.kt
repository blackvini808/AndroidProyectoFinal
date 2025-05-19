package com.example.mediahive.tmdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val service: TmdbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiService::class.java)
    }
}