package com.example.appkotlinpmdm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Activity2 : AppCompatActivity() {

    lateinit var buttonCambiarActivity2: Button
    lateinit var txtFromMainActivity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        buttonCambiarActivity2 = findViewById(R.id.buttonCambiarActivity2)
        txtFromMainActivity = findViewById(R.id.txtFromMainActivity)

        val textoDesdeMain = intent.getStringExtra("textoDesdeMain")
        txtFromMainActivity.text = textoDesdeMain

        buttonCambiarActivity2.setOnClickListener {
            cambiaActivity()
        }
    }

    fun cambiaActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
