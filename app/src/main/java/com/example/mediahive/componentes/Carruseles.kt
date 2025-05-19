package com.example.mediahive.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mediahive.modelos.Contenido

@Composable
fun CarruselHorizontal(
    titulo: String,
    items: List<Contenido>,
    modifier: Modifier = Modifier,
    onItemClick: (Contenido) -> Unit = {} // 👈 Nuevo parámetro
) {
    Column(modifier = modifier) {
        // Título
        Text(
            text = titulo,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        // Items
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { contenido ->
                ItemContenido(
                    contenido = contenido,
                    onClick = { onItemClick(contenido) } // 👈 Propaga el evento
                )
            }
        }
    }
}

@Composable
fun ItemContenido(
    contenido: Contenido,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(120.dp)
            .clickable(onClick = onClick)
    ) {
        // Portada (placeholder - reemplázalo con Coil después)
        AsyncImage(
            model = if (contenido.imagenUrl.isNotEmpty()) {
                contenido.imagenUrl
            } else {
                contenido.colorPlaceholder // Fallback al color si no hay URL
            },
            contentDescription = contenido.titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(contenido.colorPlaceholder) // Color mientras carga
        )
        // Textos
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = contenido.titulo,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = contenido.genero.uppercase(),
                color = Color(0xFF1DCD9F),  // Verde de tu tema
                fontSize = 12.sp,
                maxLines = 1,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}