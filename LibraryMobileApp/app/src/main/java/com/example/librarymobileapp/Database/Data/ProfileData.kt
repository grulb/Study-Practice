package com.example.librarymobileapp.Database.Data

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("readerID") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("registrationDate") val date: String,
    @SerializedName("status") val status: String,
)