package com.example.dami_t2_magallanes_alexandra.ui.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dami_t2_magallanes_alexandra.data.preferences.SessionManager
import com.example.dami_t2_magallanes_alexandra.ui.theme.* // Importa tus colores

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scrollState = rememberScrollState()

    var nombres by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    var mostrarSheetGenero by remember { mutableStateOf(false) }
    var mostrarSheetEstadoCivil by remember { mutableStateOf(false) }

    val opcionesGenero = listOf("Masculino", "Femenino", "No especificado")
    val opcionesEstadoCivil = listOf("Soltero", "Casado", "Viudo", "Divorciado")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Blanco // Color de fondo de la rúbrica
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Registro de Nuevo Usuario",
                color = Azul,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(value = nombres, onValueChange = { nombres = it }, label = { Text("Nombres", color = Plomo) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = apellidoPaterno, onValueChange = { apellidoPaterno = it }, label = { Text("Apellido paterno", color = Plomo) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = apellidoMaterno, onValueChange = { apellidoMaterno = it }, label = { Text("Apellido materno", color = Plomo) }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = dni, onValueChange = { dni = it }, label = { Text("DNI", color = Plomo) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())

            Box(modifier = Modifier.fillMaxWidth().clickable { mostrarSheetGenero = true }) {
                OutlinedTextField(
                    value = genero,
                    onValueChange = {},
                    label = { Text("Género (Seleccionar)", color = Plomo) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false, // Deshabilitado para que no abra teclado, pero el Box detecta el clic
                    colors = OutlinedTextFieldDefaults.colors(disabledTextColor = Negro)
                )
            }

            OutlinedTextField(value = celular, onValueChange = { celular = it }, label = { Text("N° de celular", color = Plomo) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo electrónico", color = Plomo) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso (kg)", color = Plomo) }, modifier = Modifier.weight(1f), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
                OutlinedTextField(value = altura, onValueChange = { altura = it }, label = { Text("Altura (m)", color = Plomo) }, modifier = Modifier.weight(1f), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
            }

            Box(modifier = Modifier.fillMaxWidth().clickable { mostrarSheetEstadoCivil = true }) {
                OutlinedTextField(
                    value = estadoCivil,
                    onValueChange = {},
                    label = { Text("Estado civil (Seleccionar)", color = Plomo) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(disabledTextColor = Negro)
                )
            }

            OutlinedTextField(
                value = otp,
                onValueChange = { if (it.length <= 6) otp = it }, // Limita a 6 dígitos
                label = { Text("Código OTP (6 dígitos)", color = Plomo) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombres.isBlank() || apellidoPaterno.isBlank() || dni.isBlank() || celular.isBlank() || otp.length != 6) {
                        Toast.makeText(context, "Llene los campos obligatorios y asegure 6 dígitos en OTP", Toast.LENGTH_SHORT).show()
                    } else {
                        // Unimos los apellidos para guardarlos en el SessionManager
                        val apellidosCompletos = "$apellidoPaterno $apellidoMaterno"
                        sessionManager.saveRegistro(nombres, apellidosCompletos, dni, genero, celular, correo, peso, altura, estadoCivil, otp)
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()

                        // Redirigir al Login después de registrar
                        navController.navigate("login") {
                            popUpTo("registro") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Azul)
            ) {
                Text("Guardar Registro", color = Blanco, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            }

            // Botón para ir a login si ya tiene cuenta
            TextButton(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿Ya tienes cuenta? Inicia sesión", color = Celeste)
            }
        }
    }


    if (mostrarSheetGenero) {
        ModalBottomSheet(onDismissRequest = { mostrarSheetGenero = false }, containerColor = Blanco) {
            Column(modifier = Modifier.padding(16.dp).padding(bottom = 32.dp)) {
                Text("Selecciona tu Género", style = MaterialTheme.typography.titleLarge, color = Azul, modifier = Modifier.padding(bottom = 16.dp))
                opcionesGenero.forEach { opcion ->
                    Text(
                        text = opcion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                genero = opcion
                                mostrarSheetGenero = false
                            }
                            .padding(16.dp),
                        color = Negro
                    )
                }
            }
        }
    }

    if (mostrarSheetEstadoCivil) {
        ModalBottomSheet(onDismissRequest = { mostrarSheetEstadoCivil = false }, containerColor = Blanco) {
            Column(modifier = Modifier.padding(16.dp).padding(bottom = 32.dp)) {
                Text("Selecciona tu Estado Civil", style = MaterialTheme.typography.titleLarge, color = Azul, modifier = Modifier.padding(bottom = 16.dp))
                opcionesEstadoCivil.forEach { opcion ->
                    Text(
                        text = opcion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                estadoCivil = opcion
                                mostrarSheetEstadoCivil = false
                            }
                            .padding(16.dp),
                        color = Negro
                    )
                }
            }
        }
    }
}