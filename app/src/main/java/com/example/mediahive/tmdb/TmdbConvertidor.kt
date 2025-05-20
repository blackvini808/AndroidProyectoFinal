package com.example.mediahive.tmdb

import com.example.mediahive.modelos.Contenido

fun TmdbItem.toContenido(): Contenido {
    return Contenido(
        id = this.id,
        titulo = this.title ?: this.name ?: "Sin título",
        genero = "", // Puedes mapear géneros después
        tipo = this.media_type ?: "movie",
        sinopsis = this.overview?: "",
        imagenUrl = this.poster_path?.let {
            "https://image.tmdb.org/t/p/w500$it"
        } ?: ""
    )
}