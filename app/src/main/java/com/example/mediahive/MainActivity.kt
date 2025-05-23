package com.example.mediahive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediahive.pantallas.PantallaDespues
import com.example.mediahive.pantallas.PantallaHome
import com.example.mediahive.pantallas.PantallaMiLista
import com.example.mediahive.ui.theme.MediaHiveTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MediaHiveTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "inicio"
                ) {
                    // Pantalla de inicio/autenticación
                    composable("inicio") {
                        PantallaInicio(
                            navController = navController
                        )
                    }

                    // Pantalla principal
                    composable("home") {
                        PantallaHome(
                            navController = navController,  // ✅ Cambio: Hice el parámetro no-nullable
                            onVerMiLista = { navController.navigate("mi_lista") },
                            onVerDespues = { navController.navigate("ver_despues") }  // ✅ Cambio: Añadí este parámetro
                        )
                    }

                    // Pantalla Mi Lista
                    composable("mi_lista") {
                        PantallaMiLista(
                            onBack = { navController.popBackStack() }
                        )
                    }
                    // Pantalla Ver Después (nueva)
                    composable("ver_despues") {  // ✅ Cambio: Añadí esta pantalla
                        PantallaDespues(
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(
    navController: NavController
) {
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
                onClick = { navController.navigate("home")},
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