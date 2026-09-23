package com.santibanez.saludplus.model

data class Doctor(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Double,
    val resenas: Int,
    val experiencia: String,
    val descripcion: String
)

object DoctorRepository {
    val medicos = listOf(
        Doctor(
            id = 1,
            nombre = "Dra. Ana Torres",
            especialidad = "Cardióloga",
            calificacion = 4.9,
            resenas = 128,
            experiencia = "12 años exp.",
            descripcion = "Especialista en arritmias e hipertensión, formación en la Clínica Mayo."
        ),
        Doctor(
            id = 2,
            nombre = "Dr. Luis Vega",
            especialidad = "Pediatra",
            calificacion = 4.7,
            resenas = 95,
            experiencia = "8 años exp.",
            descripcion = "Especialista en desarrollo infantil y neonatología."
        ),
        Doctor(
            id = 3,
            nombre = "Dra. Rosa Díaz",
            especialidad = "Dermatóloga",
            calificacion = 4.8,
            resenas = 110,
            experiencia = "10 años exp.",
            descripcion = "Especialista en dermatología clínica y tratamientos preventivos."
        )
    )
}