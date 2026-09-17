package com.example.mygpmadmin.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val MyGPMBlue = Color(0xFF81A6C6)
private val MyGPMDeepBlue = Color(0xFF244B70)
private val MyGPMText = Color(0xFF172333)
private val MyGPMBeige = Color(0xFFF3E3D0)

@Composable
fun SuccessScreen(
    navController: NavController,
    role: String
) {

    val isTeacher = role == "teacher"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        color = MyGPMBeige,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(78.dp)
                        .background(
                            color = MyGPMBlue,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = Color.White,
                        modifier = Modifier.size(46.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "Profile Created\nSuccessfully",
                color = MyGPMText,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = if (isTeacher) {
                    "Your teacher profile has been set up successfully."
                } else {
                    "Your CR profile has been set up successfully."
                },
                color = MyGPMText.copy(alpha = 0.65f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "You're all ready to use MyGPM.",
                color = MyGPMText.copy(alpha = 0.65f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(42.dp)
            )

            Button(
                onClick = {
                    if (isTeacher) {
                        navController.navigate("teacher_dashboard") {
                            popUpTo("teacher_profile") {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate("cr_dashboard") {
                            popUpTo("cr_profile") {
                                inclusive = true
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyGPMDeepBlue
                )
            ) {

                Text(
                    text = "Continue to Dashboard",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}