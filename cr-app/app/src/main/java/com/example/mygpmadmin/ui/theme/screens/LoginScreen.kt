package com.example.mygpmadmin.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mygpmadmin.R
import com.example.mygpmadmin.ui.theme.MyGPMBlue
import com.example.mygpmadmin.ui.theme.MyGPMDeepBlue
import com.example.mygpmadmin.ui.theme.MyGPMText

private val MyGPMErrorBackground = Color(0xFFFFEDED)
private val MyGPMErrorBorder = Color(0xFFE8A8A8)
private val MyGPMErrorText = Color(0xFF9B3D3D)

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var loginAsFaculty by remember {
        mutableStateOf(true)
    }

    // Validation errors
    var usernameError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8FC))
    ) {

        // =====================================================
        // BACKGROUND DECORATIONS
        // =====================================================

        Image(
            painter = painterResource(R.drawable.bg_building),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .alpha(0.12f),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.bg_paper_plane),
            contentDescription = null,
            modifier = Modifier
                .size(width = 280.dp, height = 360.dp)
                .align(Alignment.BottomStart)
                .offset(x = 0.dp, y = 35.dp)
                .alpha(0.15f),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(R.drawable.bg_logo_gpm),
            contentDescription = null,
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.Center)
                .alpha(0.10f),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(R.drawable.bg_graduation),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 45.dp)
                .alpha(0.12f),
            contentScale = ContentScale.Fit
        )


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 28.dp,
                    end = 28.dp,
                    top = 65.dp,
                    bottom = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =================================================
            // BRANDING
            // =================================================

            Text(
                modifier = Modifier.offset(y = 75.dp),
                text = "MyGPM Admin",
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MyGPMDeepBlue
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .offset(y = 75.dp)
            ) {

                HorizontalDivider(
                    modifier = Modifier.width(35.dp),
                    thickness = 1.dp,
                    color = MyGPMText.copy(alpha = 0.2f)
                )

                Text(
                    text = "Your classroom management, simplified.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = MyGPMText.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .offset(y = (-7).dp)
                )

                HorizontalDivider(
                    modifier = Modifier.width(35.dp),
                    thickness = 1.dp,
                    color = MyGPMText.copy(alpha = 0.2f)
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // =================================================
            // GLASS LOGIN CARD
            // =================================================

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 55.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White.copy(alpha = 0.45f))
                    .border(
                        BorderStroke(
                            1.dp,
                            Color.White.copy(alpha = 0.5f)
                        ),
                        RoundedCornerShape(28.dp)
                    )
                    .padding(
                        horizontal = 24.dp,
                        vertical = 30.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Log in to continue",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyGPMDeepBlue
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // =================================================
                // LOGIN TYPE
                // =================================================

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(4.dp)
                ) {

                    // FACULTY
                    Button(
                        onClick = {
                            loginAsFaculty = true
                            usernameError = ""
                            passwordError = ""
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (loginAsFaculty) {
                                MyGPMDeepBlue
                            } else {
                                Color.Transparent
                            },
                            contentColor = if (loginAsFaculty) {
                                Color.White
                            } else {
                                MyGPMDeepBlue
                            }
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "Faculty",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }


                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )


                    // CR
                    Button(
                        onClick = {
                            loginAsFaculty = false
                            usernameError = ""
                            passwordError = ""
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!loginAsFaculty) {
                                MyGPMDeepBlue
                            } else {
                                Color.Transparent
                            },
                            contentColor = if (!loginAsFaculty) {
                                Color.White
                            } else {
                                MyGPMDeepBlue
                            }
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.Badge,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "CR",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )


                // =================================================
                // USERNAME
                // =================================================

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            "Username / ID",
                            color = MyGPMText.copy(alpha = 0.5f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MyGPMDeepBlue
                        )
                    },
                    singleLine = true,
                    isError = usernameError.isNotEmpty(),
                    supportingText = {
                        if (usernameError.isNotEmpty()) {
                            Text(
                                text = usernameError
                            )
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MyGPMText,
                        unfocusedTextColor = MyGPMText,
                        errorContainerColor = MyGPMErrorBackground,
                        focusedContainerColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.Transparent
                        },
                        unfocusedContainerColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.Transparent
                        },
                        focusedIndicatorColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMDeepBlue
                        },
                        unfocusedIndicatorColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMDeepBlue.copy(alpha = 0.3f)
                        },
                        cursorColor = MyGPMDeepBlue
                    )
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )


                // =================================================
                // PASSWORD
                // =================================================

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            "Password",
                            color = MyGPMText.copy(alpha = 0.5f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MyGPMDeepBlue
                        )
                    },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = passwordError.isNotEmpty(),
                    supportingText = {
                        if (passwordError.isNotEmpty()) {
                            Text(
                                text = passwordError
                            )
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MyGPMText,
                        unfocusedTextColor = MyGPMText,
                        errorContainerColor = MyGPMErrorBackground,
                        focusedContainerColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.Transparent
                        },
                        unfocusedContainerColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.Transparent
                        },
                        focusedIndicatorColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMDeepBlue
                        },
                        unfocusedIndicatorColor = if (usernameError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMDeepBlue.copy(alpha = 0.3f)
                        },
                        cursorColor = MyGPMDeepBlue
                    )
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // =================================================
                // SIGN IN
                // =================================================

                Button(
                    onClick = {

                        usernameError = ""
                        passwordError = ""

                        val enteredUsername = username.trim()
                        val enteredPassword = password.trim()

                        // -----------------------------------------
                        // PASSWORD VALIDATION
                        // -----------------------------------------

                        if (enteredPassword.isEmpty()) {
                            passwordError = "Please enter your password."
                        } else if (enteredPassword != "12345") {
                            passwordError = "Incorrect password."
                        }


                        // -----------------------------------------
                        // USERNAME VALIDATION
                        // -----------------------------------------

                        if (enteredUsername.isEmpty()) {

                            usernameError = "Please enter your username."

                        } else {

                            if (loginAsFaculty) {

                                // Faculty username example:
                                // dipaligosavi@itteacher

                                val facultyUsernameRegex =
                                    Regex("^[A-Za-z]+@itteacher$")

                                if (!facultyUsernameRegex.matches(enteredUsername)) {
                                    usernameError =
                                        "Enter a valid faculty username."
                                }

                            } else {

                                // CR username example:
                                // SM24IF001@gpm.ac.in
                                //
                                // Uppercase/lowercase both accepted.

                                val crUsernameRegex =
                                    Regex("^[A-Za-z]{2}\\d{2}[A-Za-z]{2}\\d{3}@gpm\\.ac\\.in$")

                                if (!crUsernameRegex.matches(enteredUsername)) {
                                    usernameError =
                                        "Enter a valid enrollment-based username."
                                }
                            }
                        }


                        // -----------------------------------------
                        // CURRENT NAVIGATION
                        // -----------------------------------------

                        if (
                            usernameError.isEmpty() &&
                            passwordError.isEmpty()
                        ) {

                            if (loginAsFaculty) {
                                navController.navigate(
                                    "teacher_profile/${java.net.URLEncoder.encode(username, "UTF-8")}"
                                )
                            } else {
                                navController.navigate("cr_profile")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MyGPMDeepBlue
                    )
                ) {

                    Text(
                        text = "Log In",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            // =================================================
            // FOOTER
            // =================================================

            Spacer(
                modifier = Modifier.height(17.dp)
            )
        }
    }
}