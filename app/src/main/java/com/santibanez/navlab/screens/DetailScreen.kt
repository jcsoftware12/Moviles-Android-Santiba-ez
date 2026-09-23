package com.santibanez.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, studentId: Int) {
    val student = sampleStudents.find { it.id == studentId } ?: sampleStudents.first()

    val email = if (student.id == 1) "jose.santibanez@tecsup.edu.pe"
    else "${student.name.lowercase().replace(" ", ".")}@tecsup.edu.pe"

    val faculty = "Tecnologías de la Información"
    val bio = "Estudiante destacado de la carrera de ${student.major}. " +
            "Apasionado por la innovación tecnológica, el desarrollo de aplicaciones móviles " +
            "con Android y Jetpack Compose, y el diseño de arquitecturas de software modernas."

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expediente Académico",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F9FA))
                .verticalScroll(rememberScrollState())
        ) {
            // Cabecera morada con foto de perfil circular superpuesta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(color = Color(0xFF5C4DB1))
                )

                // Avatar circular superpuesto en el centro
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFEADDFF),
                    shadowElevation = 6.dp,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.BottomCenter)
                        .border(3.dp, Color.White, CircleShape)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = student.initials,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF21005D)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre y Carrera
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = student.major,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF5C4DB1),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tarjeta de detalles académicos
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "INFORMACIÓN ACADÉMICA",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5C4DB1),
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DetailItem(
                        icon = Icons.Default.Badge,
                        label = "ID Estudiante",
                        value = student.code
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = Color(0xFFE7E0EC)
                    )

                    DetailItem(
                        icon = Icons.Default.Email,
                        label = "Correo Institucional",
                        value = email
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = Color(0xFFE7E0EC)
                    )

                    DetailItem(
                        icon = Icons.Default.School,
                        label = "Facultad",
                        value = faculty
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = Color(0xFFE7E0EC)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Biografía",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D1B20)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = bio,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF49454F),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = Color(0xFFF3EDF7),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF5C4DB1),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF79747E)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1D1B20)
            )
        }
    }
}