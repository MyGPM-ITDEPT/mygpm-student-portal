package com.example.mygpmadmin.ui.theme.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygpmadmin.ui.theme.screens.AssignmentsScreen
import com.example.mygpmadmin.ui.theme.screens.CRProfileScreen
import com.example.mygpmadmin.ui.theme.screens.HomePageScreen
import com.example.mygpmadmin.ui.theme.screens.LoginScreen
import com.example.mygpmadmin.ui.theme.screens.ManageCRAccessScreen
import com.example.mygpmadmin.ui.theme.screens.ManageStudentsScreen
import com.example.mygpmadmin.ui.theme.screens.ResourceNotesScreen
import com.example.mygpmadmin.ui.theme.screens.SuccessScreen
import com.example.mygpmadmin.ui.theme.screens.TeacherProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "teacher_profile/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { entry ->
            TeacherProfileScreen(
                navController = navController,
                username = entry.arguments?.getString("username") ?: ""
            )
        }

        composable("cr_profile") {
            CRProfileScreen(navController = navController)
        }

        composable(
            route = "success/{role}",
            arguments = listOf(navArgument("role") { type = NavType.StringType })
        ) { entry ->
            SuccessScreen(
                navController = navController,
                role = entry.arguments?.getString("role") ?: "cr"
            )
        }

        composable("teacher_dashboard") {
            HomePageScreen(navController, role = "teacher", fullName = "Teacher")
        }

        composable("cr_dashboard") {
            HomePageScreen(navController, role = "cr", fullName = "CR User")
        }

        composable("cr_permissions") {
            ManageCRAccessScreen(navController = navController)
        }

        // Real screens must be registered before the generic placeholder routes.
        composable("students") {
            ManageStudentsScreen(navController = navController, role = "teacher")
        }

        composable("resources") {
            ResourceNotesScreen(
                navController = navController,
                role = "teacher"
            )
        }

        composable("assignments") {
            AssignmentsScreen(
                navController = navController,
                role = "teacher"
            )
        }

        val featureRoutes = listOf(
            "attendance" to "Attendance",
            "notices" to "Notices",
            "timetable" to "Timetable",
            "curriculum" to "Curriculum Tracker",
            "analytics" to "Analytics",
            "my_permissions" to "My CR Permissions",
            "mis_portal" to "MIS Portal",
            "notifications" to "Notifications",
            "profile_settings" to "Profile / Settings",
            "settings" to "Settings",
            "geofencing" to "Geofencing",
            "more" to "More"
        )

        featureRoutes.forEach { (route, title) ->
            composable(route) {
                FeaturePlaceholderScreen(title)
            }
        }
    }
}

@Composable
private fun FeaturePlaceholderScreen(title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6F0))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, color = Color(0xFF1A2340), fontSize = 28.sp)
        Text(
            "This dedicated screen will be designed next.",
            color = Color(0xFF1A2340).copy(alpha = 0.65f),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
