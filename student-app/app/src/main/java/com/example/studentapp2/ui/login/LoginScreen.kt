
package com.example.studentapp2.ui.login // TODO: replace with your actual package name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentapp2.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success) onLoginSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyGpmBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            // Top row: two corner icon chips, like the reference (cap + book)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconChip(Icons.Filled.School)
                IconChip(Icons.Filled.MenuBook)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "MyGPM",
                style = MaterialTheme.typography.displaySmall,
                color = MyGpmTextPrimary
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Student Login",
                style = MaterialTheme.typography.headlineMedium,
                color = MyGpmPrimary
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Welcome Back! \uD83D\uDC4B",
                style = MaterialTheme.typography.titleLarge,
                color = MyGpmTextPrimary
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Hey, Student! Ready to continue your journey?",
                style = MaterialTheme.typography.bodyMedium,
                color = MyGpmTextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(28.dp))

            LoginCard(
                formState = formState,
                uiState = uiState,
                onStudentIdChange = viewModel::onStudentIdChange,
                onPasswordChange = viewModel::onPasswordChange,
                onTogglePasswordVisibility = viewModel::onTogglePasswordVisibility,
                onLoginClick = viewModel::onLoginClick
            )

            Spacer(Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.VerifiedUser,
                    contentDescription = null,
                    tint = MyGpmPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Secure & Private",
                    style = MaterialTheme.typography.labelLarge,
                    color = MyGpmTextSecondary
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun IconChip(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MyGpmIconChipBackground),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = MyGpmPrimary)
    }
}

@Composable
private fun LoginCard(
    formState: LoginFormState,
    uiState: LoginUiState,
    onStudentIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit
) {
    val isLoading = uiState is LoginUiState.Loading

    Card(
        shape = MyGpmShapes.large,
        colors = CardDefaults.cardColors(containerColor = MyGpmSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MyGpmIconChipBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = MyGpmPrimary)
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Login to your account",
                    style = MaterialTheme.typography.titleMedium,
                    color = MyGpmPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(20.dp))

            MyGpmTextField(
                value = formState.studentId,
                onValueChange = onStudentIdChange,
                label = "User ID",
                placeholder = "Enter your User ID",
                leadingIcon = Icons.Filled.Person,
                keyboardType = KeyboardType.Text,
                errorText = formState.studentIdError,
                enabled = !isLoading
            )

            Spacer(Modifier.height(16.dp))

            MyGpmTextField(
                value = formState.password,
                onValueChange = onPasswordChange,
                label = "Password",
                placeholder = "Enter your password",
                leadingIcon = Icons.Filled.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isPasswordVisible = formState.isPasswordVisible,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
                errorText = formState.passwordError,
                enabled = !isLoading
            )

            if (uiState is LoginUiState.Error) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = uiState.message,
                    color = MyGpmError,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                enabled = !isLoading,
                shape = MyGpmShapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyGpmPrimary,
                    contentColor = MyGpmOnPrimary,
                    disabledContainerColor = MyGpmPrimaryLight
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MyGpmOnPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text("Login", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun MyGpmTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    errorText: String? = null,
    enabled: Boolean = true
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            label = { Text(label) },
            placeholder = { Text(placeholder, color = MyGpmTextHint) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = MyGpmPrimary) },
            trailingIcon = {
                if (isPassword && onTogglePasswordVisibility != null) {
                    val icon = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = onTogglePasswordVisibility) {
                        Icon(icon, contentDescription = "Toggle password visibility", tint = MyGpmTextSecondary)
                    }
                }
            },
            visualTransformation = if (isPassword && !isPasswordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = errorText != null,
            singleLine = true,
            shape = MyGpmShapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MyGpmPrimary,
                unfocusedBorderColor = MyGpmBorder,
                errorBorderColor = MyGpmError
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorText != null) {
            Text(
                text = errorText,
                color = MyGpmError,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}