package com.example.dami_t2_magallanes_alexandra.ui.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dami_t2_magallanes_alexandra.ui.screens.*
import com.example.dami_t2_magallanes_alexandra.ui.theme.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // El BottomBar solo se muestra en Inicio y Perfil
    val mostrarBottomBar = currentRoute == AppDestinations.Inicio.route || currentRoute == AppDestinations.Perfil.route

    Scaffold(
        bottomBar = {
            if (mostrarBottomBar) {
                NavigationBar(
                    containerColor = Blanco,
                    contentColor = Azul
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = currentRoute == AppDestinations.Inicio.route,
                        onClick = {
                            navController.navigate(AppDestinations.Inicio.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Blanco,
                            selectedTextColor = Azul,
                            indicatorColor = Azul,
                            unselectedIconColor = Plomo,
                            unselectedTextColor = Plomo
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = currentRoute == AppDestinations.Perfil.route,
                        onClick = {
                            navController.navigate(AppDestinations.Perfil.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Blanco,
                            selectedTextColor = Azul,
                            indicatorColor = Azul,
                            unselectedIconColor = Plomo,
                            unselectedTextColor = Plomo
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(AppDestinations.Login.route) { LoginScreen(navController) }
            composable(AppDestinations.Registro.route) { RegistroScreen(navController) }
            composable(AppDestinations.Inicio.route) { InicioScreen() }
            composable(AppDestinations.Perfil.route) { PerfilScreen() }
        }
    }
}