package com.example.mediahive.pantallas

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mediahive.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.mediahive.componentes.CarruselHorizontal
import com.example.mediahive.modelos.Contenido
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchText.value,
        onValueChange = { newValue ->  // Corregido: usa nombre explícito en lugar de 'it'
            searchText.value = newValue
        },
        placeholder = {
            Text(
                text = "¿Qué quieres ver?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Gray.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {  // Corregido: Icon debe estar dentro de leadingIcon/trailingIcon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color(0xFF1DCD9F),
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Center,
            color = Color.White
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF333333),
            focusedContainerColor = Color(0xFF444444),
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            cursorColor = Color(0xFF1DCD9F),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun PantallaHome() {
    val context = LocalContext.current
    val activity = context as? Activity
    val contenidoSeleccionado = remember { mutableStateOf<Contenido?>(null) }

    val transition = updateTransition(
        targetState = contenidoSeleccionado.value != null,
        label = "modalTransition"
    )

    val modalAlpha: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 300) },
        label = "modalAlpha",
        targetValueByState = { visible -> if (visible) 1f else 0f }
    )

    val modalScale: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 300, easing = FastOutSlowInEasing)
        },
        label = "modalScale",
        targetValueByState = { visible -> if (visible) 1f else 0.9f }
    )
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
            /////////////////////////////////////////////////////////////////////////////////////////

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
                                .offset(x = 0.dp, y = 0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clocklista),
                                contentDescription = "Volver",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF1DCD9F)
                            )
                        }
                        // Barra de búsqueda centrada
                        val searchText = remember { mutableStateOf(TextFieldValue("")) }
                        SearchBar(
                            searchText = searchText,
                            modifier = Modifier
                                .weight(1f)  // Ocupa el espacio disponible entre botones
                                .padding(horizontal = 8.dp)
                        )
                        //BOTÓN LISTA
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .zIndex(1f)
                                .size(40.dp)
                                .offset(x = (-0).dp, y = 0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "Volver",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF1DCD9F)
                            )
                        }
                    }

                    ////////////////////////////////////////////////////////////////////////////////
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)  //Ocupa todo el espacio restante
                            .padding(horizontal = 8.dp)
                    ) {
                        // Carrusel 1: RECOMENDADOS
                        item {
                            CarruselHorizontal(
                                titulo = "RECOMENDADOS",
                                items = listOf(
                                    Contenido(1, "Stranger Things", "Sci-Fi", Color(0xFF03DAC6)),
                                    Contenido(2, "The Mandalorian", "Acción", Color(0xFF03DAC6)),
                                    Contenido(3, "Dark", "Misterio", Color(0xFF03DAC6))
                                ),
                                onItemClick = {contenido -> contenidoSeleccionado.value = contenido},
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                        }

                        // Carrusel 2: "MI LISTA"
                        item {
                            CarruselHorizontal(
                                titulo = "MI LISTA",
                                items = emptyList(),
                                onItemClick = {},
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                        }

                        // Carrusel 3: "TENDENCIA"
                        item {
                            CarruselHorizontal(
                                titulo = "TENDENCIA",
                                items = listOf(
                                    Contenido(6, "House of Dragons", "Fantasía", Color(0xFF03DAC6)),
                                    Contenido(7, "The Last of Us", "Aventura", Color(0xFF03DAC6))
                                ),
                                onItemClick = {contenido -> contenidoSeleccionado.value = contenido},
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                        }

                        // Carrusel 4: "PROXIMAMENTE"
                        item {
                            CarruselHorizontal(
                                titulo = "PRÓXIMAMENTE",
                                items = listOf(
                                    Contenido(8, "One Piece", "Anime", Color(0xFF03DAC6)),
                                    Contenido(9, "The Witcher", "Fantasía", Color(0xFF03DAC6))
                                ),
                                onItemClick = {contenido -> contenidoSeleccionado.value = contenido},
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                        }
                    }
                }
            }
        }
        // 🔥🔥🔥 NUEVO: Modal al final del Box principal (ANTES del último })
        if (contenidoSeleccionado.value != null) {
            PantallaSeleccion(
                contenido = contenidoSeleccionado.value!!,
                onDismiss = { contenidoSeleccionado.value = null },
                onAddToLista = {
                    // Lógica para agregar a "Mi lista"
                    contenidoSeleccionado.value = null // Cierra el modal después
                },
                onVerDespues = {
                    // Lógica para "Ver después"
                    contenidoSeleccionado.value = null // Cierra el modal después
                },
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = modalScale
                        scaleY = modalScale
                        alpha = modalAlpha
                    }
            )
        }

    }
}