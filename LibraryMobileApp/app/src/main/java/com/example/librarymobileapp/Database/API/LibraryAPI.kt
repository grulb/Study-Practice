package com.example.librarymobileapp.Database.API

import com.example.librarymobileapp.Database.Data.LibraryData
import com.example.librarymobileapp.Database.Data.LibraryID
import retrofit2.Call
import retrofit2.http.GET

interface LibraryAPI {
    @GET("api/libraries")
    fun getLibraries(): Call<List<LibraryID>>
}