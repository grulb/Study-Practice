package com.example.librarymobileapp.Database.Data

import com.google.gson.annotations.SerializedName

data class CatalogData(
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("count") val count: Long,
    @SerializedName("rating") val rating: Double,
    @SerializedName("LibraryID") val libraryID: Long
)