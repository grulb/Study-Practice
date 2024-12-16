package com.example.librarymobileapp.Database

import com.example.librarymobileapp.Database.API.LibraryAPI
import com.example.librarymobileapp.Database.API.AuthorizeAPI
import com.example.librarymobileapp.Database.API.CatalogAPI
import com.example.librarymobileapp.Database.API.ProfileAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.116.70:8080/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val libraryApi: LibraryAPI by lazy {
        retrofit.create(LibraryAPI::class.java)
    }
    val authorizeApi: AuthorizeAPI by lazy {
        retrofit.create(AuthorizeAPI::class.java)
    }
    val profileApi: ProfileAPI by lazy {
        retrofit.create(ProfileAPI::class.java)
    }
    val catalogApi: CatalogAPI by lazy {
        retrofit.create(CatalogAPI::class.java)
    }
}