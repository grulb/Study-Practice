package com.example.librarymobileapp.Database.Data

import com.google.gson.annotations.SerializedName

data class LibraryData(
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String
)