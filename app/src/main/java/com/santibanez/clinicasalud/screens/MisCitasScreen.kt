package com.santibanez.saludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santibanez.saludplus.model.Cita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(onOpenDrawer: () -> Unit) {
    // Lista mutable reactiva de citas en el estado local de Compose
    val misCitas = remember {
        mutableStateListOf(
            Cita(1, "Dra. Ana Torres", "Viernes 27", "10:30 am", "Confirmada"),
            Cita(2, "Dr. Luis Vega", "Miércoles 15", "3:00 pm", "Completada")
        )
    }

    // Estado local para controlar el dialogo de confirmacion de cancelacion
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis citas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(misCitas, key = { it.id }) { cita ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EFEF)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Barra lateral indicadora de estado
                        Box(
                            modifier = Modifier
                                .width(6.dp)
                                .height(110.dp)
                                .background(
                                    when (cita.estado) {
                                        "Confirmada" -> Color(0xFF5C4DB1)
                                        "Cancelada" -> Color(0xFFD32F2F)
                                        else -> Color(0xFF888888)
                                    }
                                )
                        )

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = cita.doctorNombre,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )

                                // Mostrar el ícono de cancelación únicamente si la cita está 'Confirmada'
                                if (cita.estado == "Confirmada") {
                                    IconButton(
                                        onClick = { citaACancelar = cita },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Cancelar cita",
                                            tint = Color(0xFFD32F2F)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${cita.fecha}, ${cita.hora}",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            // Estilo visual según el estado de la cita
                            val (backgroundColor, textColor) = when (cita.estado) {
                                "Confirmada" -> Color(0xFFE6F4EA) to Color(0xFF137333)
                                "Cancelada" -> Color(0xFFFCE8E6) to Color(0xFFC5221F)
                                else -> Color(0xFFE8EAED) to Color.DarkGray
                            }

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = backgroundColor
                            ) {
                                Text(
                                    text = cita.estado,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = textColor,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // AlertDialog de confirmación de cancelación
        citaACancelar?.let { cita ->
            AlertDialog(
                onDismissRequest = { citaACancelar = null },
                title = {
                    Text(
                        text = "Cancelar Cita",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text("¿Estás seguro de que deseas cancelar la cita con ${cita.doctorNombre}?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val index = misCitas.indexOfFirst { it.id == cita.id }
                            if (index != -1) {
                                misCitas[index] = misCitas[index].copy(estado = "Cancelada")
                            }
                            citaACancelar = null
                        }
                    ) {
                        Text(
                            text = "Sí, cancelar",
                            color = Color(0xFFD32F2F),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { citaACancelar = null }
                    ) {
                        Text("No", color = Color.Gray)
                    }
                }
            )
        }
    }
}