package com.example.taller1

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color as ComposeColor

class Config : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Preview2() //mostramos la pantalla de configuracion haciendo uso de un preview
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    Configur() //con esta funcion creamos un preview que nos permitirá ver la pantalla de configuracion
}

@Composable
fun Configur() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)
    val savedColor = preferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) } //variable que nos permite cambiar el color del fondo de la pantalla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        //utilizamos el modifier dentro de un column para poder centrar el contenido
    ) {

        Button(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
            Text("INICIO")
        }
        //creamos un boton que nos llevara a la pantalla de inicio

        Spacer(modifier = Modifier.height(16.dp)) //damos espacio

        Button(onClick = {
            backgroundColor.value = ComposeColor.White
            saveBackgroundColor(context, android.graphics.Color.WHITE)
        }) {
            Text("Blanco")
        }
        //creamos un boton que cambie el color del fondo a blanco

        Spacer(modifier = Modifier.height(16.dp)) //damos espacio

        Button(onClick = {
            backgroundColor.value = ComposeColor(0xFFaec6cf)
            saveBackgroundColor(context, android.graphics.Color.rgb(174, 198, 207))
        }) {
            Text("Azul")
        }
        //creamos un boton que cambie el color del fondo a azul

        Spacer(modifier = Modifier.height(16.dp)) //damos espacio

        Button(onClick = {
            backgroundColor.value = ComposeColor(0xFFfdfd96)
            saveBackgroundColor(context, android.graphics.Color.rgb(253, 253, 150))
        }) {
            Text("Amarillo")
        }
        //creamos un boton que cambie el color del fondo a amarillo
    }
}

fun saveBackgroundColor(context: Context, color: Int) {
    val preferences = context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)

    with(preferences.edit()) {
        putInt("backgroundColor", color) //cambiamos el fondo y lo guardamos
        apply() //aplicamos para que se cambie el color
    }
}
