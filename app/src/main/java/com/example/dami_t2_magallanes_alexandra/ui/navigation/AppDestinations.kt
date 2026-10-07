package com.example.dami_t2_magallanes_alexandra.ui.navigation

sealed class AppDestinations(val route: String) {
    data object Registro: AppDestinations("registro")
    data object Login: AppDestinations("login")
    data object Inicio: AppDestinations("inicio")
    data object Perfil: AppDestinations("perfil")
}
