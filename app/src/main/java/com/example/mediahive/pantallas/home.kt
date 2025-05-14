package com.example.mediahive.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mediahive.R

@Composable
fun PantallaHome() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo con degradado elevado
        Image(
            painter = painterResource(id = R.drawable.fondopantallas),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x004D4D4D), // transparente arriba
                            Color.White.copy(alpha = 0.20f),
                            Color(0xFF1DCD9F)
                        ),
                        startY = 0f,
                        endY = 500f // más elevado que antes
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Parte superior: Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp), // altura del banner
                contentAlignment = Alignment.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.mediahive___w),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(100.dp) // más pequeño que antes
                        .align(Alignment.Center)
                        .offset(y = 23.dp)
                )

                // Botón izquierdo (placeholder cuadrado)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 16.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                )

                // Botón derecho (placeholder cuadrado)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .padding(end = 16.dp, top = 16.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja la parte inferior al fondo
            // Parte inferior: Contenido interactivo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(690.dp) // 1940 px ≈ 690 dp
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Color(0xFF222222))
            ) {
                // Aquí irá tu contenido futuro (tabs, listas, etc.)
            }
        }
    }
}