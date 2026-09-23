package com.santibanez.saludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.santibanez.saludplus.model.DoctorRepository
import com.santibanez.saludplus.navigation.Screen

@Composable
fun ConfirmacionScreen(
    navController: NavController,
    doctorId: Int,
    fecha: String,
    hora: String
) {
    val doctor = DoctorRepository.medicos.find { it.id == doctorId } ?: DoctorRepository.medicos.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFFD2E3FC), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF0D652D),
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Cita agendada!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = doctor.nombre,
            fontSize = 15.sp,
            color = Color.Gray
        )

        Text(
            text = "Viernes 27, 10:30 am",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                navController.navigate(Screen.MisCitas.route) {
                    popUpTo(Screen.Home.route)
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFE8F8)),
            modifier = Modifier
                .width(200.dp)
                .height(48.dp)
        ) {
            Text("Ver mis citas", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}