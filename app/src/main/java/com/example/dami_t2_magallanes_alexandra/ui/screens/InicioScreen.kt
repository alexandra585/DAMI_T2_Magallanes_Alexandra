package com.example.dami_t2_magallanes_alexandra.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dami_t2_magallanes_alexandra.ui.theme.*

@Composable
fun InicioScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "¡Bienvenido a la App de Ale :D!",
                color = Azul,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Has iniciado sesión correctamente.",
                color = Plomo,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}