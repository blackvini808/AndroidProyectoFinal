package com.example.mediahive.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediahive.modelos.Contenido

@Composable
fun ItemLista(
    contenido: Contenido,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .aspectRatio(2f / 3)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        color = contenido.colorPlaceholder,
        onClick = { onClick?.invoke() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = contenido.titulo,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            // Indicador de estado (opcional)
            if (contenido.enMiLista || contenido.paraVerDespues) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF1DCD9F))
                )
            }
        }
    }
}

// Preview para visualización
@Preview
@Composable
fun ItemListaPreview() {
    ItemLista(
        contenido = Contenido(
            id = 1,
            titulo = "Ejemplo de Película",
            genero = "Acción",
            sinopsis = "",
            colorPlaceholder = Color(0xFF03DAC6),
            enMiLista = true,
            paraVerDespues = true,
        )
    )
}