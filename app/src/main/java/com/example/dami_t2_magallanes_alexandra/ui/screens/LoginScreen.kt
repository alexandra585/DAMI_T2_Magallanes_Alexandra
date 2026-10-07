package com.example.dami_t2_magallanes_alexandra.ui.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dami_t2_magallanes_alexandra.data.preferences.SessionManager
import com.example.dami_t2_magallanes_alexandra.ui.theme.*
import com.example.dami_t2_magallanes_alexandra.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    var telefono by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Blanco
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.licon_moon), // Cambia "logo_app" por el nombre de tu imagen
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(120.dp) // Aquí puedes hacerla más grande o pequeña cambiando el número
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Iniciar Sesión",
                color = Azul,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(value = telefono, onValueChange = { if (it.length <= 9)
                telefono = it }, label = { Text("N° de celular", color = Plomo)
                                         }, keyboardOptions = KeyboardOptions
                (keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth())

            OutlinedTextField(
                value = otp,
                onValueChange = {
                    if (it.length <= 6) otp = it // Limita estrictamente a 6 dígitos
                },
                label = { Text("Código OTP (6 dígitos)", color = Plomo) },
                visualTransformation = PasswordVisualTransformation(), // Oculta los números como contraseña
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
            )

            Button(
                onClick = {
                    // Validaciones de la rúbrica
                    if (telefono.length != 9) {
                        Toast.makeText(context, "El teléfono debe tener exactamente 9 dígitos", Toast.LENGTH_SHORT).show()
                    } else if (otp.length != 6) {
                        Toast.makeText(context, "El OTP debe tener exactamente 6 dígitos", Toast.LENGTH_SHORT).show()
                    } else {
                        // Verificamos si los datos coinciden con lo guardado en SharedPreferences
                        val esValido = sessionManager.validarLogin(telefono, otp)
                        if (esValido) {
                            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            // Navegar al Menú de Inicio
                            navController.navigate("inicio") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Teléfono u OTP incorrectos. ¿Ya te registraste?", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Azul)
            ) {
                Text("Ingresar", color = Blanco, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para ir a registrarse si no tiene cuenta
            TextButton(
                onClick = { navController.navigate("registro") }
            ) {
                Text("¿No tienes cuenta? Regístrate aquí", color = Celeste)
            }
        }
    }
}