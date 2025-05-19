package com.example.mediahive.estados

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mediahive.modelos.Contenido

object EstadoListas {
    val miLista: SnapshotStateList<Contenido> = mutableStateListOf()
    val verDespues: SnapshotStateList<Contenido> = mutableStateListOf()

    // Función para agregar contenido a "Mi Lista"
    fun agregarAMiLista(contenido: Contenido) {
        if (!miLista.any { it.id == contenido.id }) {
            miLista.add(contenido.copy(enMiLista = true))
        }
    }

    // Función para agregar a "Ver Después"
    fun agregarAVerDespues(contenido: Contenido) {
        if (!verDespues.any { it.id == contenido.id }) {
            verDespues.add(contenido.copy(paraVerDespues = true))
        }
    }

    // Función para remover contenido
    fun removerDeListas(contenido: Contenido) {
        miLista.removeAll { it.id == contenido.id }
        verDespues.removeAll { it.id == contenido.id }
    }
}