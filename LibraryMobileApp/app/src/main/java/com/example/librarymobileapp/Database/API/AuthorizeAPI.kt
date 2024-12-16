package com.example.librarymobileapp.Database.API

import com.example.librarymobileapp.Database.Data.LoginData
import com.example.librarymobileapp.Database.Data.RegisterData
import com.example.librarymobileapp.Database.Responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizeAPI {
    @POST("api/users/login")
    fun login(@Body loginRequest: LoginData): Call<LoginResponse>

    @POST("api/users/register")
    fun register(@Body registerRequest: RegisterData): Call<Int>
}