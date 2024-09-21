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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar
import androidx.compose.ui.graphics.Color as ComposeColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //con esta funcion conseguimos ver a pantalla completa y sin bordes
        setContent {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                } //gracias al scaffold y lo que esta programado dentro conseguimos que el contenido se adapte a la pantalla y a como queremos que se visualice
        }
    }
}

@Composable
fun Greeting(modifier: Modifier) {

    val context = LocalContext.current
    val preferences = context.getSharedPreferences("BackgroundPrefs", MODE_PRIVATE)

    val savedColor = preferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) }
    //estas variables las utilizaremos para poder cambiar el color de fondo de la pantalla cuando se haga algun cambio en la activity de configuracion

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        //utilizamos el modifier dentro de un column para poder centrar el contenido
    ) {

        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) //usamos una variable que guarde la hora en la que se esta haciendo uso de la aplicación
        val bienvenida = when {
            hora < 13 -> "Buenos días"
            hora < 20 -> "Buenas tardes"
            else -> "Buenas noches"
        }
        //creamos una variable bienvenida que cambiara dependiendo de la hora en la que se use la aplicación

        Text(text = bienvenida) //campo de texto para poder mostrar el mensaje de bienvenida

        Spacer(modifier = Modifier.height(16.dp)) //damos un espacio para que el boton no se vea muy pegado al texto

        val context = LocalContext.current //creamos una variable que almacene el contextp de la aplicación

        Button(onClick = {
            context.startActivity(
                Intent(context, Inicio::class.java)
            )
        }) {
            Text("APLICACIÓN")
        }
        //creamos un boton que nos llevara a la pantalla de inicio
    }
}

fun getComposeColor(color: Int): ComposeColor {
    return ComposeColor(
        android.graphics.Color.red(color) / 255f,
        android.graphics.Color.green(color) / 255f,
        android.graphics.Color.blue(color) / 255f,
        android.graphics.Color.alpha(color) / 255f
    )
    //esta funcion sera una funcion que utilizaremos en todas las clases para poder cambiar el color
}