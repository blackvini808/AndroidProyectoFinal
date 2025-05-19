package com.example.mediahive.tmdb

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("search/multi")
    suspend fun buscarContenido(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): TmdbResponse
}