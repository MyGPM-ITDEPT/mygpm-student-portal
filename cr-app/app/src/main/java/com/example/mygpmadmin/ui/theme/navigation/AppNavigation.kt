package com.example.mygpmadmin.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mygpmadmin.ui.theme.screens.LoginScreen

@Composable
fun AppNavigation() {

    // Controls movement between different screens
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // -----------------------------------------
        // LOGIN
        // -----------------------------------------

        composable("login") {
            LoginScreen(navController = navController)
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