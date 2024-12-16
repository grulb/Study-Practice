package com.example.librarymobileapp.Database.Data

import com.google.gson.annotations.SerializedName

data class LibraryID(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String
)