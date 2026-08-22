package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.adapter.AdapterUsuario
import com.example.chatapp.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private var adapterUsuario: AdapterUsuario? = null
    private var listaUsuarios: MutableList<Usuario>? = null
    private var rvUsuarios: RecyclerView? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvUsuarios = findViewById(R.id.rv_usuarios)
        rvUsuarios?.setHasFixedSize(true)
        rvUsuarios?.layoutManager = LinearLayoutManager(this)

        searchView = findViewById(R.id.search_view)

        listaUsuarios = mutableListOf()
        ObtenerUsuarios()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                BuscarUsuario(newText?.lowercase())
                return true
            }
        })
    }

    private fun ObtenerUsuarios() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance().reference.child("usuarios")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (listaUsuarios as MutableList<Usuario>).clear()
                if (searchView?.query.toString().isEmpty()) {
                    for (dataSnapshot in snapshot.children) {
                        val usuario: Usuario? = dataSnapshot.getValue(Usuario::class.java)
                        if (usuario != null && usuario.uid != firebaseUser?.uid) {
                            (listaUsuarios as MutableList<Usuario>).add(usuario)
                        }
                    }
                    adapterUsuario = AdapterUsuario(this@MainActivity, listaUsuarios!!)
                    rvUsuarios?.adapter = adapterUsuario
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun BuscarUsuario(nombre: String?) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val consulta = FirebaseDatabase.getInstance().reference.child("usuarios")
            .orderByChild("buscar").startAt(nombre).endAt(nombre + "\uf8ff")

        consulta.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (listaUsuarios as MutableList<Usuario>).clear()
                for (dataSnapshot in snapshot.children) {
                    val usuario: Usuario? = dataSnapshot.getValue(Usuario::class.java)
                    if (usuario != null && usuario.uid != firebaseUser?.uid) {
                        (listaUsuarios as MutableList<Usuario>).add(usuario)
                    }
                }
                adapterUsuario = AdapterUsuario(this@MainActivity, listaUsuarios!!)
                rvUsuarios?.adapter = adapterUsuario
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error en la búsqueda", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_salir -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity, Inicio::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}