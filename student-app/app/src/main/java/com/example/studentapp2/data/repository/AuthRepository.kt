package com.example.studentapp2.data.repository

import com.example.studentapp2.data.model.LoginResponse

class AuthRepository {

    suspend fun login(
        identifier: String,
        password: String
    ): Result<LoginResponse> {
        return Result.failure(
            Exception("Login endpoint is not connected yet.")
        )
    }

    suspend fun forgotPassword(
        identifier: String
    ): Result<Unit> {
        return Result.failure(
            Exception("Forgot-password endpoint is not connected yet.")
        )
    }

    suspend fun getCurrentUser(): Result<LoginResponse> {
        return Result.failure(
            Exception("User endpoint is not connected yet.")
        )
    }

    suspend fun logout(): Result<Unit> {
        return Result.success(Unit)
    }
}