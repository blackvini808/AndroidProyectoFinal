package com.example.mediahive.modelos

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder

// Versión con URLs (comenta la anterior)
data class Contenido(
    val id: Int,
    val titulo: String,
    val genero: String,
    val colorPlaceholder: Color,  // Ej: "https://image.tmdb.org/t/p/w500/xyz.jpg"
    val esFavorito: Boolean = false,
)

val listaEjemplo = listOf(
    Contenido(
        id = 1,
        titulo = "Breaking Bad",
        genero = "Drama",
        colorPlaceholder = Color(0xFF03DAC6)
    )
)