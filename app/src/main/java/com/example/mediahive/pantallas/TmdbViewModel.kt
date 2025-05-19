package com.example.mediahive.pantallas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediahive.modelos.Contenido
import com.example.mediahive.tmdb.TmdbClient
import com.example.mediahive.tmdb.toContenido
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope  // ← Esta es la importante
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TmdbViewModel : ViewModel() {
    private val _resultadosBusqueda = MutableStateFlow<List<Contenido>>(emptyList())
    val resultadosBusqueda: StateFlow<List<Contenido>> = _resultadosBusqueda.asStateFlow()

    private val _estaCargando = MutableStateFlow(false)
    val estaCargando: StateFlow<Boolean> = _estaCargando.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun buscarContenido(query: String) {
        viewModelScope.launch {  // ← Ahora debería reconocer viewModelScope
            _estaCargando.value = true
            try {
                val response = TmdbClient.service.buscarContenido(
                    query = query,
                    apiKey = "TU_API_KEY"  // No olvides poner tu API key real aquí
                )
                _resultadosBusqueda.value = response.results.map { it.toContenido() }
                _error.value = null
            } catch (e: Exception) {
                Log.e("BUSQUEDA", "Error: ${e.message}")
                _error.value = "Error al buscar. Verifica tu conexión."
                _resultadosBusqueda.value = emptyList()
            } finally {
                _estaCargando.value = false
            }
        }
    }
}