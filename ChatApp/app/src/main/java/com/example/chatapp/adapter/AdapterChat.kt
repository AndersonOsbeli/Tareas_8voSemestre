package com.example.chatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.model.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AdapterChat(private val context: Context, private val listaChat: List<Chat>) :
    RecyclerView.Adapter<AdapterChat.ViewHolder>() {

    private val MSG_TYPE_IZQUIERDA = 0
    private val MSG_TYPE_DERECHA = 1
    var fUser: FirebaseUser? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == MSG_TYPE_DERECHA) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_chat_derecha, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_chat_izquierda, parent, false)
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = listaChat[position]
        holder.show_mensaje.text = chat.mensaje
    }

    override fun getItemCount(): Int {
        return listaChat.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var show_mensaje: TextView = itemView.findViewById(R.id.show_mensaje)
    }

    override fun getItemViewType(position: Int): Int {
        fUser = FirebaseAuth.getInstance().currentUser
        return if (listaChat[position].emisor == fUser!!.uid) {
            MSG_TYPE_DERECHA
        } else {
            MSG_TYPE_IZQUIERDA
        }
    }
}