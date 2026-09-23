package com.santibanez.saludplus.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santibanez.saludplus.navigation.Screen

@Composable
fun DrawerContent(
    currentRoute: String,
    onDestinationClicked: (String) -> Unit
) {
    val purpleColor = Color(0xFF5C4DB1)

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(purpleColor.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JS",
                        fontWeight = FontWeight.Bold,
                        color = purpleColor,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Jose Santibáñez",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Paciente",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

            NavigationDrawerItem(
                label = { Text("Inicio") },
                selected = currentRoute == Screen.Home.route,
                onClick = { onDestinationClicked(Screen.Home.route) },
                icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            NavigationDrawerItem(
                label = { Text("Mis citas") },
                selected = currentRoute == Screen.MisCitas.route,
                onClick = { onDestinationClicked(Screen.MisCitas.route) },
                icon = { Icon(Icons.Outlined.DateRange, contentDescription = null) },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            NavigationDrawerItem(
                label = { Text("Historial médico") },
                selected = currentRoute == Screen.HistorialMedico.route,
                onClick = { onDestinationClicked(Screen.HistorialMedico.route) },
                icon = { Icon(Icons.Outlined.History, contentDescription = null) },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}