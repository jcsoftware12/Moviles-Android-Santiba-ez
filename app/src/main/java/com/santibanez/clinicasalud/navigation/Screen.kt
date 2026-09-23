package com.santibanez.saludplus.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DoctorProfile : Screen("doctor_profile/{doctorId}") {
        fun createRoute(doctorId: Int) = "doctor_profile/$doctorId"
    }
    object AgendarCita : Screen("agendar_cita/{doctorId}") {
        fun createRoute(doctorId: Int) = "agendar_cita/$doctorId"
    }
    object Confirmacion : Screen("confirmacion/{doctorId}/{fecha}/{hora}") {
        fun createRoute(doctorId: Int, fecha: String, hora: String) =
            "confirmacion/$doctorId/$fecha/$hora"
    }
    object MisCitas : Screen("mis_citas")
    object HistorialMedico : Screen("historial_medico")
}