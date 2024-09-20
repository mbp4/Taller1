package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

class Inicio: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            GreetingPreview()
        }
    }
}

@Composable
fun lectorNombre(){

    val nombre = remember { mutableStateOf("") }
    Column {
        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        Button(onClick = {

        }) {
            Text("Guardar")
        }

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        Text(text = "Bienvenido " + nombre.value)

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        BotonConfiguracion()
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lectorNombre()
}


@Composable
fun BotonConfiguracion(){

    Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(
            Intent(context, Config:: class.java)
        )
    }) {
        Text("CONFIGURACION")
    }
}







