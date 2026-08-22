package com.example.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.ChatActivity
import com.example.chatapp.R
import com.example.chatapp.model.Usuario

class AdapterUsuario(private val context: Context, private val listaUsuarios: List<Usuario>) :
    RecyclerView.Adapter<AdapterUsuario.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.nombre.text = usuario.n_usuario
        holder.email.text = usuario.email

        if (usuario.imagen != null && usuario.imagen != "") {
            Glide.with(context).load(usuario.imagen).placeholder(R.mipmap.ic_launcher).into(holder.imagen)
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).into(holder.imagen)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("uid", usuario.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.item_usuario_nombre)
        val email: TextView = itemView.findViewById(R.id.item_usuario_email)
        val imagen: ImageView = itemView.findViewById(R.id.item_usuario_imagen)
    }
}