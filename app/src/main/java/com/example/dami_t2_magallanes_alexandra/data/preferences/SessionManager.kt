package com.example.dami_t2_magallanes_alexandra.data.preferences

import android.content.Context

class SessionManager(context: Context) {
    private val preferences = context.getSharedPreferences(
        "app_sesion_t2",
        Context.MODE_PRIVATE
    )

    fun saveRegistro(
        nombres: String, apellidos: String, dni: String,
        genero: String, celular: String, correo: String,
        peso: String, altura: String, estadoCivil: String,
        otp: String
    ) {
        preferences.edit()
            .putBoolean("isRegistered", true)
            .putString("nombres", nombres)
            .putString("apellidos", apellidos)
            .putString("dni", dni)
            .putString("genero", genero)
            .putString("celular", celular)
            .putString("correo", correo)
            .putString("peso", peso)
            .putString("altura", altura)
            .putString("estadoCivil", estadoCivil)
            .putString("otp", otp) // Guardamos el PIN de 6 dígitos
            .apply()
    }


    fun validarLogin(celularInput: String, otpInput: String): Boolean {
        val savedCelular = preferences.getString("celular", "")
        val savedOtp = preferences.getString("otp", "")
        return celularInput == savedCelular && otpInput == savedOtp
    }

    fun isRegistered(): Boolean {
        return preferences.getBoolean("isRegistered", false)
    }


    fun getPerfilData(): Map<String, String> {
        return mapOf(
            "Nombres" to (preferences.getString("nombres", "") ?: ""),
            "Apellidos" to (preferences.getString("apellidos", "") ?: ""),
            "DNI" to (preferences.getString("dni", "") ?: ""),
            "Género" to (preferences.getString("genero", "") ?: ""),
            "Celular" to (preferences.getString("celular", "") ?: ""),
            "Correo" to (preferences.getString("correo", "") ?: ""),
            "Peso" to (preferences.getString("peso", "") ?: ""),
            "Altura" to (preferences.getString("altura", "") ?: ""),
            "Estado Civil" to (preferences.getString("estadoCivil", "") ?: "")
        )
    }
}