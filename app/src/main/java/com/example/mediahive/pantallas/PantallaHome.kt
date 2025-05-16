package com.example.mediahive.pantallas

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mediahive.R

@Composable
fun PantallaHome() {
    val context = LocalContext.current
    val activity = context as? Activity

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
                        .height(120.dp) // más pequeño que antes
                        .align(Alignment.Center)
                        .offset(y = 45.dp)
                )

                // Botón izquierdo (placeholder cuadrado)
                IconButton(
                    onClick = { activity?.finish() },
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.TopStart)
                        .offset(x = 16.dp, y = 36.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.backarrow),
                        contentDescription = "Volver",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }

                // Botón derecho (placeholder cuadrado)
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.TopStart)
                        .offset(x = 352.dp, y = 36.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bars),
                        contentDescription = "OPCIONES",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                }
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
                Column(modifier = Modifier.fillMaxSize()){
                    // Parte superior fija: Botones y barra de búsqueda
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        //BOTÓN LISTA DESPUÉS
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .zIndex(1f)
                                .size(40.dp)
                                .offset(x = 16.dp, y = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clocklista),
                                contentDescription = "Volver",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF1DCD9F)
                            )
                        }
                        // Barra de búsqueda centrada

                        //BOTÓN LISTA
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .zIndex(1f)
                                .size(40.dp)
                                .offset(x = (-16).dp, y = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "Volver",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF1DCD9F)
                            )
                        }
                    }
                }
            }
        }
    }
}