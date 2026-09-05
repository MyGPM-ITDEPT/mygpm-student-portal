package com.example.studentapp2.ui.login // TODO: replace with your actual package name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapp2.data.model.LoginResponse
import com.example.studentapp2.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginFormState(
    val studentId: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val studentIdError: String? = null,
    val passwordError: String? = null
)

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState
    data class Success(val response: LoginResponse) : LoginUiState
    data class Error(val message: String) : LoginUiState
}

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onStudentIdChange(value: String) {
        _formState.value = _formState.value.copy(studentId = value, studentIdError = null)
    }

    fun onPasswordChange(value: String) {
        _formState.value = _formState.value.copy(password = value, passwordError = null)
    }

    fun onTogglePasswordVisibility() {
        _formState.value = _formState.value.copy(isPasswordVisible = !_formState.value.isPasswordVisible)
    }

    fun onLoginClick() {
        val current = _formState.value

        val studentIdError = if (current.studentId.isBlank()) "Enter your User ID" else null
        val passwordError = if (current.password.isBlank()) "Enter your password" else null

        if (studentIdError != null || passwordError != null) {
            _formState.value = current.copy(
                studentIdError = studentIdError,
                passwordError = passwordError
            )
            return
        }

        // Prevent repeated submissions while a request is in flight
        if (_uiState.value is LoginUiState.Loading) return

        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            repository.login(current.studentId.trim(), current.password)
                .onSuccess { response ->
                    _uiState.value = LoginUiState.Success(response)
                }
                .onFailure { throwable ->
                    _uiState.value = LoginUiState.Error(mapErrorToStudentMessage(throwable))
                }
        }
    }

    /** Never surface raw exceptions/stack traces to the student. */
    private fun mapErrorToStudentMessage(throwable: Throwable): String {
        val raw = throwable.message.orEmpty()
        return when {
            raw.contains("Invalid login credentials", ignoreCase = true) ->
                "Incorrect User ID or password. Please try again."
            raw.contains("network", ignoreCase = true) ||
                    raw.contains("timeout", ignoreCase = true) ||
                    raw.contains("unable to resolve host", ignoreCase = true) ->
                "No internet connection. Please check your network and try again."
            else -> "Something went wrong. Please try again in a moment."
        }
    }
}