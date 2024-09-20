package com.example.taller1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

class Config: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            GreetingPreview2()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
   Configur();
}

@Composable
fun Configur() {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,)
    {


        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        val context2 = LocalContext.current
        Button(onClick = {
            context2.startActivity(
                Intent(
                    context2,
                    Inicio::class.java
                )
            )
        }) {
            Text("INICIO")
        }
    }

}

