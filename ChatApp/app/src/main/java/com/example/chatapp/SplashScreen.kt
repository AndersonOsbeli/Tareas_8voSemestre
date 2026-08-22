package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Llamada a la función
        mostrarBienvenida()
    }

    // La función debe ir fuera de onCreate
    private fun mostrarBienvenida() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Puedes dejarlo vacío
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreen, Inicio::class.java)
                startActivity(intent)
                finish()
            }
        }.start() // <-- IMPORTANTE: Agregar .start() para que corra el temporizador
    }
}