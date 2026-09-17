
package com.example.mygpmadmin.data.repository

class AuthRepository {

    suspend fun login(
        username: String,
        password: String
    ): Result<String> {

        // Supabase authentication will be added
        // after Aman provides the project details.

        return Result.failure(
            Exception("Supabase connection is not configured yet.")
        )
    }
}