package com.example.mediahive.tmdb

data class TmdbResponse(
    val results: List<TmdbItem>
)

data class TmdbItem(
    val id: Int,
    val title: String?,
    val name: String?,
    val poster_path: String?,
    val media_type: String?,
    val overview: String?
)