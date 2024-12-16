package com.example.librarymobileapp.Database.Data

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("registrationDate") val date: String,
    @SerializedName("status") val status: String,
    @SerializedName("role") val role: String,
    @SerializedName("library") val library: LibraryData
)