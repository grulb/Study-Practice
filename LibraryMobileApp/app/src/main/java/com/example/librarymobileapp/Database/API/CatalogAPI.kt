package com.example.librarymobileapp.Database.API

import com.example.librarymobileapp.Database.Data.CatalogData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogAPI {
    @GET("api/books/library/{libraryId}")
    fun getAllBooksByLibraryId(@Path("libraryId")libraryId: Long): Call<List<CatalogData>>
}