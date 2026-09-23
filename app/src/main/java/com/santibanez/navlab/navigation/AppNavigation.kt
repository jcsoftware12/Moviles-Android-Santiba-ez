package com.santibanez.navlab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.santibanez.navlab.screens.DetailScreen
import com.santibanez.navlab.screens.HomeScreen
import com.santibanez.navlab.screens.ListScreen
import com.santibanez.navlab.screens.LoginScreen
import com.santibanez.navlab.screens.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.List.route) {
            ListScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(name = "studentId") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 1
            DetailScreen(navController = navController, studentId = studentId)
        }
    }
}