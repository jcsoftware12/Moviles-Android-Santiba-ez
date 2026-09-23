package com.santibanez.saludplus.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.santibanez.saludplus.components.DrawerContent
import com.santibanez.saludplus.screens.*
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                currentRoute = currentRoute,
                onDestinationClicked = { route ->
                    scope.launch { drawerState.close() }
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
            }

            composable(
                route = Screen.DoctorProfile.route,
                arguments = listOf(navArgument("doctorId") { type = NavType.IntType })
            ) { backStackEntry ->
                val doctorId = backStackEntry.arguments?.getInt("doctorId") ?: 1
                DoctorProfileScreen(navController, doctorId)
            }

            composable(
                route = Screen.AgendarCita.route,
                arguments = listOf(navArgument("doctorId") { type = NavType.IntType })
            ) { backStackEntry ->
                val doctorId = backStackEntry.arguments?.getInt("doctorId") ?: 1
                AgendarCitaScreen(navController, doctorId)
            }

            composable(
                route = Screen.Confirmacion.route,
                arguments = listOf(
                    navArgument("doctorId") { type = NavType.IntType },
                    navArgument("fecha") { type = NavType.StringType },
                    navArgument("hora") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val doctorId = backStackEntry.arguments?.getInt("doctorId") ?: 1
                val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
                val hora = backStackEntry.arguments?.getString("hora") ?: ""
                ConfirmacionScreen(navController, doctorId, fecha, hora)
            }

            composable(Screen.MisCitas.route) {
                MisCitasScreen(onOpenDrawer = { scope.launch { drawerState.open() } })
            }

            composable(Screen.HistorialMedico.route) {
                HistorialMedicoScreen(onOpenDrawer = { scope.launch { drawerState.open() } })
            }
        }
    }
}