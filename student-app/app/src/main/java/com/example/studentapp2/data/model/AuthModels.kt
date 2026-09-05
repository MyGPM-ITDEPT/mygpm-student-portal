package com.example.studentapp2.data.model // TODO: replace with your actual package name

/**
 * Login request.
 *
 * Contract Section 5: "An enrollment-based username is proposed" but Section 20 lists
 * "Exact username format" as a decision still TO BE FINALIZED. `identifier` is used here
 * as a neutral placeholder so nothing is invented. Rename to `enrollmentNumber` / `email`
 * once the format is confirmed.
 */
data class LoginRequest(
    val identifier: String, // enrollment number / username - format TBD per contract Section 20
    val password: String
)

/**
 * Login response.
 * Contract Section 5: "Successful login returns session, user ID, role and profile."
 */
data class LoginResponse(
    val session: SessionInfo,
    val userId: String,
    val role: String, // "student" | "teacher" | "cr" per contract Section 4
    val profile: StudentProfile?
)

data class SessionInfo(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Long
)

// Mirrors the `profiles` table key fields (contract Section 17, Table 7)
data class StudentProfile(
    val id: String,
    val fullName: String,
    val enrollmentNumber: String?,
    val departmentId: String?
)

/** Standard error shape, per contract Section 16 (Standard API Errors). */
data class ApiError(
    val code: Int,      // 400 / 401 / 403 / 404 / 409 / 422 / 500
    val message: String
)