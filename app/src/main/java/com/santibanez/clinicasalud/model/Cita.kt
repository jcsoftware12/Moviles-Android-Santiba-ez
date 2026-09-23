package com.santibanez.saludplus.model

data class Cita(
    val id: Int,
    val doctorNombre: String,
    val fecha: String,
    val hora: String,
    val estado: String // "Confirmada" o "Completada"
)