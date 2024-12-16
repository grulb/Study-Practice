package com.example.librarymobileapp.Database.Responses

data class LoginResponse(
    val userId: Long?,
    val status: Int,
    val role: String?,
)