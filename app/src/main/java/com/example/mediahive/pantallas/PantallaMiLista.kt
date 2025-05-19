package com.example.mediahive.pantallas

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediahive.componentes.ItemContenido
import com.example.mediahive.estados.EstadoListas
import com.example.mediahive.modelos.Contenido
import com.example.mediahive.ui.theme.MediaHiveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMiLista(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contenidoSeleccionado = remember { mutableStateOf<Contenido?>(null)}

    val transition = updateTransition(
        targetState = contenidoSeleccionado.value != null,
        label = "modalTransition"
    )
    val modalAlpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 300) },
        label = "modalAlpha",
        targetValueByState = { visible -> if (visible) 1f else 0f }
    )
    val modalScale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
        label = "modalScale",
        targetValueByState = { visible -> if (visible) 1f else 0.9f }
    )


    Scaffold(
        modifier = modifier.background(Color(0xFF222222)),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mi Lista",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Mi Lista",
                        tint = Color(0xFF1DCD9F),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF222222)
                )
            )
        }
    ) { paddingValues ->
        Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222222)) // Refuerza el fondo
            .padding(paddingValues)
        )
        when {
            EstadoListas.miLista.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF222222))
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Tu lista está vacía",
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(), //Ocupa todo el espacio
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = EstadoListas.miLista, // O EstadoListas.verDespues en PantallaDespues
                        key = { it.id } // Usa el ID como clave única
                    ) { contenido ->
                        ItemContenido(
                            contenido = contenido,
                            onClick = { contenidoSeleccionado.value = contenido },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
        if (contenidoSeleccionado.value != null) {
            // *** ANIMACIÓN *** - Fondo semitransparente animado (mismo que PantallaHome)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f * modalAlpha)) // Alpha animado
                    .clickable { contenidoSeleccionado.value = null },
                contentAlignment = Alignment.Center
            ) {
                PantallaSeleccion(
                    contenido = contenidoSeleccionado.value!!,
                    onDismiss = { contenidoSeleccionado.value = null },
                    onAddToLista = { /* ... */ },
                    onVerDespues = { /* ... */ },
                    modifier = Modifier.graphicsLayer { // *** ANIMACIÓN *** - Escala y alpha
                        scaleX = modalScale
                        scaleY = modalScale
                        alpha = modalAlpha
                    }
                )
            }
        }

    }
}

// Añade esta preview para visualización
@Preview(showBackground = true)
@Composable
fun PreviewPantallaMiLista() {
    MediaHiveTheme {
        PantallaMiLista(onBack = {})
    }
}