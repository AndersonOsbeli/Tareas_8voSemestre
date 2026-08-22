package com.example.chatapp.model

class Chat {
    var emisor: String? = null
    var receptor: String? = null
    var mensaje: String? = null

    constructor()

    constructor(emisor: String?, receptor: String?, mensaje: String?) {
        this.emisor = emisor
        this.receptor = receptor
        this.mensaje = mensaje
    }
}