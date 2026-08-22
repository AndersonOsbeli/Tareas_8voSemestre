package com.example.chatapp.model

class Usuario {
    var uid: String? = null
    var n_usuario: String? = null
    var email: String? = null
    var imagen: String? = null
    var buscar: String? = null

    constructor()

    constructor(uid: String?, n_usuario: String?, email: String?, imagen: String?, buscar: String?) {
        this.uid = uid
        this.n_usuario = n_usuario
        this.email = email
        this.imagen = imagen
        this.buscar = buscar
    }
}