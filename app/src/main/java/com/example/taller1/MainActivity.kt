package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val greetingTextView = findViewById<TextView>(R.id.greetingText)
        val inicioButton = findViewById<Button>(R.id.inicioButton)


        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val bienvenida = when {
            hora < 13 -> "Buenos días"
            hora < 20 -> "Buenas tardes"
            else -> "Buenas noches"
        }


        greetingTextView.text = bienvenida


        inicioButton.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }
}
