package com.santibanez.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.santibanez.navlab.navigation.Screen

data class StudentItem(
    val id: Int,
    val name: String,
    val major: String,
    val code: String,
    val initials: String
)

val sampleStudents = listOf(
    StudentItem(1, "José Santibáñez", "Ingeniería de Sistemas", "2024-0001", "JS"),
    StudentItem(2, "Maria Garcia", "Diseño y Desarrollo de Software", "2024-0002", "MG"),
    StudentItem(3, "Carlos Perez", "Redes y Comunicaciones", "2024-0003", "CP"),
    StudentItem(4, "Ana Torres", "Big Data y Ciencia de Datos", "2024-0004", "AT"),
    StudentItem(5, "Luis Paredes", "Ciberseguridad", "2024-0005", "LP"),
    StudentItem(6, "Sofia Castro", "Ingeniería de Software", "2024-0006", "SC"),
    StudentItem(7, "Diego Flores", "Desarrollo Web", "2024-0007", "DF"),
    StudentItem(8, "Lucia Morales", "Inteligencia Artificial", "2024-0008", "LM")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Directorio de Alumnos",
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
                    containerColor = Color(0xFFF3EDF7)
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F9FA)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sampleStudents) { student ->
                Card(
                    onClick = {
                        navController.navigate(Screen.Detail.createRoute(student.id))
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar circular con iniciales
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xFF5C4DB1), Color(0xFF7D5260))
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = student.initials,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = student.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1D1B20)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = student.major,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF5C4DB1),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Ver expediente",
                            tint = Color(0xFF79747E),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}