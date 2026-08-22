package com.example.chatapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.adapter.AdapterChat
import com.example.chatapp.model.Chat
import com.example.chatapp.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var etMensaje: EditText
    private lateinit var btnEnviar: ImageButton
    private lateinit var rvChat: RecyclerView
    private lateinit var toolbar: Toolbar

    var uidUsuarioSeleccionado: String? = null
    var firebaseUser: FirebaseUser? = null
    var reference: DatabaseReference? = null

    var adapterChat: AdapterChat? = null
    var listaChat: MutableList<Chat>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)

        toolbar = findViewById(R.id.toolbar_chat)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        rvChat = findViewById(R.id.rv_chat)
        rvChat.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        rvChat.layoutManager = linearLayoutManager

        etMensaje = findViewById(R.id.Et_mensaje)
        btnEnviar = findViewById(R.id.Btn_enviar)

        uidUsuarioSeleccionado = intent.getStringExtra("uid")
        firebaseUser = FirebaseAuth.getInstance().currentUser

        reference = FirebaseDatabase.getInstance().reference.child("usuarios").child(uidUsuarioSeleccionado!!)

        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val usuario: Usuario? = snapshot.getValue(Usuario::class.java)
                supportActionBar?.title = usuario?.n_usuario
                LeerMensajes(firebaseUser!!.uid, uidUsuarioSeleccionado!!)
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        btnEnviar.setOnClickListener {
            val mensaje = etMensaje.text.toString()
            if (mensaje.isNotEmpty()) {
                EnviarMensaje(firebaseUser!!.uid, uidUsuarioSeleccionado!!, mensaje)
            } else {
                Toast.makeText(applicationContext, "Escribe un mensaje", Toast.LENGTH_SHORT).show()
            }
            etMensaje.setText("")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun EnviarMensaje(emisor: String, receptor: String, mensaje: String) {
        val reference = FirebaseDatabase.getInstance().reference
        val hashMap = HashMap<String, Any>()
        hashMap["emisor"] = emisor
        hashMap["receptor"] = receptor
        hashMap["mensaje"] = mensaje

        reference.child("Chats").push().setValue(hashMap)
    }

    private fun LeerMensajes(emisorId: String, receptorId: String) {
        listaChat = mutableListOf()
        val reference = FirebaseDatabase.getInstance().reference.child("Chats")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaChat!!.clear()
                for (dataSnapshot in snapshot.children) {
                    val chat = dataSnapshot.getValue(Chat::class.java)
                    if (chat != null) {
                        if (chat.receptor == emisorId && chat.emisor == receptorId ||
                            chat.receptor == receptorId && chat.emisor == emisorId
                        ) {
                            listaChat!!.add(chat)
                        }
                    }
                }
                adapterChat = AdapterChat(this@ChatActivity, listaChat!!)
                rvChat.adapter = adapterChat
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}