package com.example.taller1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val nombreInput = findViewById<EditText>(R.id.nombreInput)
        val guardarButton = findViewById<Button>(R.id.guardarButton)
        val bienvenidoText = findViewById<TextView>(R.id.bienvenidoText)
        val configButton = findViewById<Button>(R.id.configButton)

        val sharedPreferences = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE)

        val nombreGuardado = sharedPreferences.getString("nombre", "")
        bienvenidoText.text = "Bienvenido $nombreGuardado"

        guardarButton.setOnClickListener {
            val nombre = nombreInput.text.toString()
            with(sharedPreferences.edit()) {
                putString("nombre", nombre)
                apply()
            }
            bienvenidoText.text = "Bienvenido $nombre"
        }

        configButton.setOnClickListener {
            val intent = Intent(this, Config::class.java)
            startActivity(intent)
        }
    }
}
