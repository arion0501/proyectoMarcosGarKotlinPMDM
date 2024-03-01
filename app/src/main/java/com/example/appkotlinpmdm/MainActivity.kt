package com.example.appkotlinpmdm

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var layoutMini: ConstraintLayout
    lateinit var buttonCambiarColor: Button
    lateinit var buttonCambiarActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutMini = findViewById(R.id.layoutMini)
        buttonCambiarColor = findViewById(R.id.buttonCambiarColor)
        buttonCambiarActivity = findViewById(R.id.buttonCambiarActivity)

        buttonCambiarColor.setOnClickListener {
            cambiaColor()
        }

        buttonCambiarActivity.setOnClickListener {
            cambiaActivity()
        }
    }

    fun cambiaColor() {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()

        val color = Color.rgb(red, green, blue)

        layoutMini.setBackgroundColor(color)
    }

    fun cambiaActivity() {
        val inputText = findViewById<EditText>(R.id.inputTxtInicio).text.toString()
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("textoDesdeMain", inputText)
        startActivity(intent)
    }
}