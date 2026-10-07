package com.example.dami_t2_magallanes_alexandra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dami_t2_magallanes_alexandra.ui.navigation.AppNavigation
import com.example.dami_t2_magallanes_alexandra.ui.theme.DAMI_T2_Magallanes_AlexandraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMI_T2_Magallanes_AlexandraTheme {
                // Borramos el "Greeting" por defecto y arrancamos tu navegación principal
                AppNavigation()
            }
        }
    }
}