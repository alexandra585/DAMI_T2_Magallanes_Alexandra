package com.example.dami_t2_magallanes_alexandra.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dami_t2_magallanes_alexandra.data.preferences.SessionManager
import com.example.dami_t2_magallanes_alexandra.ui.theme.*

@Composable
fun PerfilScreen() {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }


    val perfilData = sessionManager.getPerfilData()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco)
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Mi Perfil",
            color = Azul,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Blanco),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                perfilData.forEach { (etiqueta, valor) ->
                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                        Text(
                            text = etiqueta,
                            color = Plomo,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = if (valor.isNotBlank()) valor else "No registrado",
                            color = Negro,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                        HorizontalDivider(
                            color = Plomo.copy(alpha = 0.2f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}