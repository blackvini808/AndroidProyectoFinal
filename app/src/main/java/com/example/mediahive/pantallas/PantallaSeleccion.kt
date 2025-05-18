package com.example.mediahive.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mediahive.modelos.Contenido

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaSeleccion(
    contenido: Contenido,
    onDismiss: () -> Unit,
    onAddToLista: () -> Unit,
    onVerDespues: () -> Unit
) {
    // Fondo semitransparente
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f))
            .clickable(onClick = onDismiss)
    ) {
        // Contenedor principal (ahora más alto)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f) // 👈 90% de altura
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFF222222)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                // Portada (más grande)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9)
                ) {
                    AsyncImage(
                        model = contenido.colorPlaceholder,
                        contentDescription = contenido.titulo,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Botón de reproducción simulado (centrado)
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xCC1DCD9F))
                            .clickable { /* Simular reproducción */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Reproducir",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                // 🔥 Título movido aquí (debajo de la imagen)
                Text(
                    text = contenido.titulo,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

                // Info básica
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = contenido.genero.uppercase(),
                        color = Color(0xFF1DCD9F),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "• 2023", // Ejemplo, ajusta según tu modelo
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                // Sinopsis
                Text(
                    text = contenido.sinopsis ?: "Descripción no disponible",
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(16.dp)
                )

                // Botones de acción (sin "Reproducir")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Botón "Mi lista"
                    Button(
                        onClick = onAddToLista,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1DCD9F)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mi lista")
                    }

                    // Botón "Ver después"
                    Button(
                        onClick = onVerDespues,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF444444)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ver después")
                    }
                }
            }
        }
    }
}