package com.example.mygpmadmin.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygpmadmin.ui.theme.screens.CRProfileScreen
import com.example.mygpmadmin.ui.theme.screens.LoginScreen
import com.example.mygpmadmin.ui.theme.screens.SuccessScreen
import com.example.mygpmadmin.ui.theme.screens.TeacherProfileScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // -----------------------------------------
        // LOGIN
        // -----------------------------------------

        composable("login") {
            LoginScreen(
                navController = navController
            )
        }

        // -----------------------------------------
        // TEACHER PROFILE
        // -----------------------------------------

        composable(
            route = "teacher_profile/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val username =
                backStackEntry.arguments?.getString("username") ?: ""

            TeacherProfileScreen(
                navController = navController,
                username = username
            )
        }

        // -----------------------------------------
        // CR PROFILE
        // -----------------------------------------

        composable("cr_profile") {
            CRProfileScreen(
                navController = navController
            )
        }

        // -----------------------------------------
        // SUCCESS PAGE
        // -----------------------------------------

        composable("success/{role}") { backStackEntry ->

            val role =
                backStackEntry.arguments?.getString("role") ?: "cr"

            SuccessScreen(
                navController = navController,
                role = role
            )
        }

        // -----------------------------------------
        // TEACHER DASHBOARD
        // -----------------------------------------

        composable("teacher_dashboard") {

            // Teacher Dashboard will go here.

        }

        // -----------------------------------------
        // CR DASHBOARD
        // -----------------------------------------

        composable("cr_dashboard") {

            // CR Dashboard will go here.

        }
    }
}