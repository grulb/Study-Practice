package com.example.librarymobileapp.Database.API

import com.example.librarymobileapp.Database.Data.ProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileAPI {
    @GET("api/users/{id}")
    fun getUserById(@Path("id") readerID: Long): Call<ProfileData>
}