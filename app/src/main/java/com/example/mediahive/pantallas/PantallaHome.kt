package com.example.mediahive.pantallas

import android.app.Activity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mediahive.R
import com.example.mediahive.componentes.CarruselHorizontal
import com.example.mediahive.componentes.ItemContenido
import com.example.mediahive.estados.EstadoListas
import com.example.mediahive.modelos.Contenido


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda(
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
fun PantallaHome(
    onVerMiLista: () -> Unit,
    onVerDespues: () -> Unit,
    navController: NavController,  // ✅ Cambio: Hice el parámetro no-nullable
    modifier: Modifier = Modifier
) {
    val viewModel: TmdbViewModel = viewModel()
    val resultadosBusqueda by viewModel.resultadosBusqueda.collectAsState()
    val estaCargando by viewModel.estaCargando.collectAsState()

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

    //**********FILTRAR BUSQUEDA***********//
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    val showSearchResults = remember { mutableStateOf(false) }

    LaunchedEffect(searchText.value) {
        if (searchText.value.text.length > 2) {
            showSearchResults.value = true
            viewModel.buscarContenido(searchText.value.text)
        } else {
            showSearchResults.value = false
        }
    }
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
                            onClick = {navController.navigate("ver_despues")},
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
                        BarraBusqueda(
                            searchText = searchText,
                            modifier = Modifier
                                .weight(1f)  // Ocupa el espacio disponible entre botones
                                .padding(horizontal = 8.dp)
                        )
                        //BOTÓN LISTA
                        IconButton(
                            onClick = { navController.navigate("mi_lista") },
                            modifier = Modifier
                                .zIndex(1f)
                                .size(40.dp)
                                .offset(x = (-0).dp, y = 0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "Mi Lista",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF1DCD9F)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        if (!showSearchResults.value) {
                            // Modo normal - Carruseles
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                CarruselHorizontal(
                                    titulo = "RECOMENDADOS",
                                    items = listOf(
                                        Contenido(66732, "Stranger Things", "Sci-Fi", "tv", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"),
                                        Contenido(82856, "The Mandalorian", "Acción", "tv", "https://image.tmdb.org/t/p/w500/eU1i6eHXlzMOlEq0ku1iwzNWqNM.jpg"),
                                        Contenido(70523, "Dark", "Misterio", "tv", "https://image.tmdb.org/t/p/w500/5Lo4H7NNUvcj8ZGS7eSZ7qV0hKb.jpg"),
                                        Contenido(1399, "Game of Thrones", "Fantasía", "tv", "https://image.tmdb.org/t/p/w500/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg"),
                                        Contenido(60735, "The Flash", "Superhéroes", "tv", "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg"),
                                        Contenido(1416, "Grey's Anatomy", "Drama", "tv", "https://image.tmdb.org/t/p/w500/eqgIOObafPJitt8JNh1LuO2fvqu.jpg"),
                                        Contenido(63174, "Lucifer", "Fantasía", "tv", "https://image.tmdb.org/t/p/w500/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"),
                                        Contenido(456, "The Simpsons", "Animación", "tv", "https://image.tmdb.org/t/p/w500/qcr9bBY6MVeLzriKCmJOv1562uY.jpg"),
                                        Contenido(1668, "Friends", "Comedia", "tv", "https://image.tmdb.org/t/p/w500/7buCWBTcyxorwQE0n6svifvEQlW.jpg"),
                                        Contenido(1421, "Modern Family", "Comedia", "tv", "https://image.tmdb.org/t/p/w500/fu5vEUHgxkAPmX26ISQXqHmlPMq.jpg"),
                                        Contenido(120472, "The Witcher", "Fantasía", "tv", "https://image.tmdb.org/t/p/w500/7vjaCdMw15FEbXyLQTVa04URsPm.jpg"),
                                        Contenido(71712, "The Good Doctor", "Drama", "tv", "https://image.tmdb.org/t/p/w500/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"),
                                        Contenido(69050, "Riverdale", "Drama", "tv", "https://image.tmdb.org/t/p/w500/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"),
                                        Contenido(46261, "Vikings", "Historia", "tv", "https://image.tmdb.org/t/p/w500/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg"),
                                        Contenido(1396, "Breaking Bad", "Drama", "tv", "https://image.tmdb.org/t/p/w500/3xnWaLQjelJDDF7LT1WBo6f4BRe.jpg")
                                    ),
                                    onItemClick = { contenido -> contenidoSeleccionado.value = contenido },
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )

                                CarruselHorizontal(
                                    titulo = "MI LISTA",
                                    items = EstadoListas.miLista,
                                    onItemClick = { contenido -> contenidoSeleccionado.value = contenido },
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )

                                CarruselHorizontal(
                                    titulo = "TENDENCIA",
                                    items = listOf(
                                        Contenido(76600, "Avatar: The Way of Water", "Ciencia ficción", "movie", "https://image.tmdb.org/t/p/w500/94xxm5701CzlOdgD4Gfz1l7Y6hC.jpg"),
                                        Contenido(505642, "Black Panther: Wakanda Forever", "Acción", "movie", "https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg"),
                                        Contenido(536554, "M3GAN", "Terror", "movie", "https://image.tmdb.org/t/p/w500/d9nBoowhjiiYc4FBNtQkPY7c11H.jpg"),
                                        Contenido(315162, "Puss in Boots: The Last Wish", "Animación", "movie", "https://image.tmdb.org/t/p/w500/kuf6dutpsT0vSVehic3EZIqkOBt.jpg"),
                                        Contenido(640146, "Ant-Man and the Wasp: Quantumania", "Superhéroes", "movie", "https://image.tmdb.org/t/p/w500/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg"),
                                        Contenido(385687, "Fast X", "Acción", "movie", "https://image.tmdb.org/t/p/w500/fiVW06jE7z9YnO4trhaMEdclSiC.jpg"),
                                        Contenido(594767, "Shazam! Fury of the Gods", "Aventura", "movie", "https://image.tmdb.org/t/p/w500/A3ZbZsmsvNGdprRi2lKgGEeVLEH.jpg"),
                                        Contenido(646389, "Plane", "Acción", "movie", "https://image.tmdb.org/t/p/w500/qi9r5xBgcc9KTxlOLjssEbDgO0J.jpg"),
                                        Contenido(677179, "Creed III", "Drama", "movie", "https://image.tmdb.org/t/p/w500/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg"),
                                        Contenido(603692, "John Wick: Chapter 4", "Acción", "movie", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg"),
                                        Contenido(502356, "The Super Mario Bros. Movie", "Animación", "movie", "https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg")
                                    ),
                                    onItemClick = { contenido -> contenidoSeleccionado.value = contenido },
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )

                                CarruselHorizontal(
                                    titulo = "PRÓXIMAMENTE",
                                    items = listOf(
                                        Contenido(447365, "Guardians of the Galaxy Vol. 3", "Superhéroes", "movie", "https://image.tmdb.org/t/p/w500/r2J02Z2OpNTctfOSN1Ydgii51I3.jpg"),
                                        Contenido(569094, "Spider-Man: Across the Spider-Verse", "Animación", "movie", "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"),
                                        Contenido(385687, "Fast X", "Acción", "movie", "https://image.tmdb.org/t/p/w500/fiVW06jE7z9YnO4trhaMEdclSiC.jpg"),
                                        Contenido(447277, "The Little Mermaid", "Fantasía", "movie", "https://image.tmdb.org/t/p/w500/ym1dxyOk4jFcSl4Q2zmRrA5BEEN.jpg"),
                                        Contenido(615656, "Meg 2: The Trench", "Aventura", "movie", "https://image.tmdb.org/t/p/w500/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg"),
                                        Contenido(667538, "Transformers: Rise of the Beasts", "Ciencia ficción", "movie", "https://image.tmdb.org/t/p/w500/gPbM0MK8CP8A174rmUwGsADNYKD.jpg"),
                                        Contenido(298618, "The Flash", "Superhéroes", "movie", "https://image.tmdb.org/t/p/w500/rktDFPbfHfUbArZ6OOOKsXcv0Bm.jpg"),
                                        Contenido(603692, "John Wick: Chapter 4", "Acción", "movie", "https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg"),
                                        Contenido(502356, "The Super Mario Bros. Movie", "Animación", "movie", "https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg"),
                                    ),
                                    onItemClick = { contenido -> contenidoSeleccionado.value = contenido },
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )
                            }
                        } else {
                            // Modo búsqueda - Cuadrícula
                            if (resultadosBusqueda.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "No se encontraron coincidencias",
                                        color = Color.White.copy(alpha = 0.6f),
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            } else {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(8.dp)
                                ) {
                                    items(resultadosBusqueda.size) { index ->
                                        val contenido = resultadosBusqueda[index]
                                        ItemContenido(
                                            contenido = contenido,
                                            onClick = { contenidoSeleccionado.value = contenido },
                                            modifier = Modifier.padding(4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        if (contenidoSeleccionado.value != null) {
            PantallaSeleccion(
                contenido = contenidoSeleccionado.value!!,
                onDismiss = { contenidoSeleccionado.value = null },
                onAddToLista = {
                    EstadoListas.agregarAMiLista(contenidoSeleccionado.value!!)  // ✅ Cambio: Usa la función helper
                    contenidoSeleccionado.value = null
                },
                onVerDespues = {
                    EstadoListas.agregarAVerDespues(contenidoSeleccionado.value!!)  // ✅ Cambio: Usa la función helper
                    contenidoSeleccionado.value = null
                },
                modifier = Modifier.graphicsLayer {
                    scaleX = modalScale
                    scaleY = modalScale
                    alpha = modalAlpha
                }
            )
        }
    }
}