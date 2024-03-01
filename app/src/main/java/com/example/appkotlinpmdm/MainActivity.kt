package com.example.appkotlinpmdm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var layoutMini: ConstraintLayout
    lateinit var buttonCambiarColor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutMini = findViewById(R.id.layoutMini)
        buttonCambiarColor = findViewById(R.id.buttonCambiarColor)

        buttonCambiarColor.setOnClickListener {
            cambiaColor()
        }
    }

    fun cambiaColor() {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()

        val color = Color.rgb(red, green, blue)

        layoutMini.setBackgroundColor(color)
    }
}