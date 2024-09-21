package com.example.taller1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun lectorNombre(context: Context) {
    val nombre = remember { mutableStateOf("") }

    // Recupera el color de fondo guardado en las preferencias
    val sharedPreferences = remember {
        context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)
    }
    val savedColor = sharedPreferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),  // Aplica el color de fondo recuperado
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            with(sharedPreferences.edit()) {
                putString("nombre", nombre.value)
                apply()
                context.startActivity(
                    Intent(context, Inicio::class.java)
                )
            }
        }) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val nombreGuardado = remember { sharedPreferences.getString("nombre", "") ?: "" }

        // Usar AndroidView para mostrar el saludo como un TextView
        AndroidView(factory = { ctx ->
            TextView(ctx).apply {
                text = "Bienvenido $nombreGuardado"
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        BotonConfiguracion()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    lectorNombre(context)
}

@Composable
fun BotonConfiguracion() {
    Spacer(modifier = Modifier.height(16.dp))

    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(
            Intent(context, Config::class.java)
        )
    }) {
        Text("CONFIGURACION")
    }
}
