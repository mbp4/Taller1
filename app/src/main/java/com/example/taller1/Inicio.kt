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
            GreetingPreview() //mostramos la pantalla de inicio haciendo uso de un preview
        }
    }
}

@Composable
fun lectorNombre(context: Context) {
    val nombre = remember { mutableStateOf("") } //creamos una variable que cambie su estado en el caso de que esta se modifique, inicialmente aparece vacia

    val sharedPreferences = remember {
        context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)
    }
    val savedColor = sharedPreferences.getInt("backgroundColor", android.graphics.Color.WHITE)
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
        TextField(
            value = nombre.value, //cambiamos el valor del nombre por el introducido por el usuario
            onValueChange = { nombre.value = it }, //actualizamos la variable para el nombre
            label = { Text("Nombre") }
            //creamos un textfield para que el usuario pueda introducir su nombre
        )

        Spacer(modifier = Modifier.height(16.dp)) //damos una separacion

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
        //con este boton guardamos el nombre y hacemos que la pagina se reinice para que el usuario vea el nombre que ha introducido

        Spacer(modifier = Modifier.height(16.dp)) //damos un espacio

        val nombreGuardado = remember { sharedPreferences.getString("nombre", "") ?: "" }
        //creamos una variable que guarde el nombre que ha guardado el usuario

        AndroidView(factory = { ctx ->
            TextView(ctx).apply {
                text = "Bienvenido $nombreGuardado"
            }
        })
        //usamos AndroidView para poder mostrar el nombre que ha guardado el usuario haciendo uso del textView

        Spacer(modifier = Modifier.height(16.dp)) //damos un espacio

        BotonConfiguracion() //llamamos a la funcion que crea el boton de configuracion
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    lectorNombre(context)
    //llamamos a la funcion para que nos de un preview de la pantalla
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

    //creamos un boton que nos llevara a la pantalla de configuracion
}
