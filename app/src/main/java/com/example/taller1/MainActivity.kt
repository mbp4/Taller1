package com.example.taller1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taller1.ui.theme.Taller1Theme
import java.util.Calendar
import java.util.Calendar.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Taller1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
}


@Composable
fun Greeting(modifier: Modifier) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        val hora = Calendar.getInstance().get(HOUR_OF_DAY)
        val bienvenida = when {
            hora < 12 -> "Buenos dias"
            hora < 20 -> "Buenas tardes"
            else -> "Buenas noches"
        }

        Text(text = bienvenida.toString())

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(onClick = {
            context.startActivity(
                Intent(
                    context,
                    Inicio::class.java
                )
            )
        }) {
            Text("INICIO")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Taller1Theme {
        Greeting(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }
}
