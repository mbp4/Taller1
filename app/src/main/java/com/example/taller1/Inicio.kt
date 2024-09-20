package com.example.taller1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
fun lectorNombre(context: Context){

    val nombre = remember { mutableStateOf("") }

    val sharedPreferences = remember {
        context.getSharedPreferences("misPreferencias", Context.MODE_PRIVATE)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,)
    {

        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        Button(onClick = {
            with(sharedPreferences.edit()) {
                putString("nombre", nombre.value)
                apply()
            }
        }) {
            Text("Guardar")
        }

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        val nombreGuardado = remember { sharedPreferences.getString("nombre", "") ?: "" }
        Text(text = "Bienvenido " + nombreGuardado)

        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

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







