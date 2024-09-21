package com.example.taller1

import android.app.Activity.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taller1.ui.theme.Taller1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {





                // Aplicamos el color de fondo usando el estado mutable backgroundColor
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(backgroundColor.value) // Aplicar el color de fondo
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

        }
    }
}

@Composable
fun Greeting(modifier: Modifier) {

    val context = LocalContext.current
    val preferences = context.getSharedPreferences("BackgroundPrefs", MODE_PRIVATE)

    // Se inicializa el color guardado en las preferencias
    val savedColor = preferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),  // Aplica el color de fondo recuperado
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val bienvenida = when {
            hora < 13 -> "Buenos días"
            hora < 20 -> "Buenas tardes"
            else -> "Buenas noches"
        }

        Text(text = bienvenida)

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(onClick = {
            context.startActivity(
                Intent(context, Inicio::class.java)
            )
        }) {
            Text("INICIO")
        }
    }
}



// Esta función convierte el color en formato Android a formato Compose
fun getComposeColor(color: Int): ComposeColor {
    return ComposeColor(
        android.graphics.Color.red(color) / 255f,
        android.graphics.Color.green(color) / 255f,
        android.graphics.Color.blue(color) / 255f,
        android.graphics.Color.alpha(color) / 255f
    )
}