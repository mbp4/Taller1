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
            GreetingPreview2()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Configur()
}

@Composable
fun Configur() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)
    val savedColor = preferences.getInt("backgroundColor", android.graphics.Color.WHITE)
    val backgroundColor = remember { mutableStateOf(getComposeColor(savedColor)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
            Text("INICIO")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            backgroundColor.value = ComposeColor.White
            saveBackgroundColor(context, android.graphics.Color.WHITE)
        }) {
            Text("Blanco")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            backgroundColor.value = ComposeColor.Blue
            saveBackgroundColor(context, android.graphics.Color.BLUE)
        }) {
            Text("Azul")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            backgroundColor.value = ComposeColor.Yellow
            saveBackgroundColor(context, android.graphics.Color.YELLOW)
        }) {
            Text("Amarillo")
        }
    }
}

fun saveBackgroundColor(context: Context, color: Int) {
    val preferences = context.getSharedPreferences("BackgroundPrefs", Context.MODE_PRIVATE)

    with(preferences.edit()) {
        putInt("backgroundColor", color)
        apply()
    }
}
