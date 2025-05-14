package com.example.mediahive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediahive.ui.theme.MediaHiveTheme
import kotlinx.coroutines.delay
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MediaHiveTheme {
                PantallaInicio()
            }
        }
    }
}

@Composable
fun PantallaInicio() {
    val mostrarBoton = remember { mutableStateOf(false) }

    // Mostrar botón luego de 2 segundos
    LaunchedEffect(Unit) {
        delay(2000)
        mostrarBoton.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Imagen con recorte redondeado en esquina inferior derecha
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .align(Alignment.TopCenter)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 90.dp // 390px ó 195dp
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.fondopantallas),
                contentDescription = "Fondo TVs",
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
            )

            // Degradado superpuesto
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x004D4D4D), // Arriba transparente
                                Color(0x331FFFFFFF), // Blanco con 20%
                                Color(0xFF1DCD9F)     // Verde solido
                            )
                        )
                    )
            )
        }

        // Logo centrado ligeramente arriba
        Image(
            painter = painterResource(id = R.drawable.mediahive___w),
            contentDescription = "Logo MediaHive",
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-110).dp)
                .height(275.dp)
        )

        // Botón con aparición suave
        AnimatedVisibility(
            visible = mostrarBoton.value,
            enter = fadeIn(),
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 300.dp) // Aprox. 471px desde el centro
        ) {
            Button(
                onClick = { /* TODO: Navegar a pantalla Home */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DCD9F)),
                modifier = Modifier
                    .width(215.dp)
                    .height(50.dp)
            ) {
                Text("ENTRAR", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
