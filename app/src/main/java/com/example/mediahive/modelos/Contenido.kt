package com.example.mediahive.modelos

import androidx.compose.ui.graphics.Color

data class Contenido(
    val id: Int,                  // ID de TMDB
    val titulo: String,
    val genero: String,
    val tipo: String = "movie",    // Valor por defecto ("movie" o "tv")
    val imagenUrl: String = "",   // URL completa ej: "https://image.tmdb.org/t/p/w500/abc123.jpg"
    val sinopsis: String,
    val esFavorito: Boolean = false,
    val enMiLista: Boolean = false,
    val paraVerDespues: Boolean = false,
    val colorPlaceholder: Color = generarColorAleatorio()
) {
    companion object {
        fun generarColorAleatorio(): Color {
            return listOf(
                Color(0xFF1DCD9F),
                Color(0xFF03DAC6),
                Color(0xFFFF4081)
            ).random()
        }
    }
}