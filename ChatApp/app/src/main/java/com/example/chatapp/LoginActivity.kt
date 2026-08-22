package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var Et_email: TextInputEditText
    private lateinit var Et_password: TextInputEditText
    private lateinit var Btn_login: Button
    private lateinit var Txt_ir_registro: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Et_email = findViewById(R.id.L_Et_email)
        Et_password = findViewById(R.id.L_Et_password)
        Btn_login = findViewById(R.id.Btn_login)
        Txt_ir_registro = findViewById(R.id.txt_ir_registro)
        auth = FirebaseAuth.getInstance()

        Btn_login.setOnClickListener {
            val email = Et_email.text.toString()
            val password = Et_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                LogearUsuario(email, password)
            }
        }

        Txt_ir_registro.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun LogearUsuario(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ha ocurrido un error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}