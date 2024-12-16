package com.example.librarymobileapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.librarymobileapp.Database.Data.LoginData
import com.example.librarymobileapp.Database.RetrofitClient
import com.example.librarymobileapp.Database.API.AuthorizeAPI
import com.example.librarymobileapp.Database.Responses.LoginResponse
import com.example.librarymobileapp.databinding.ActivityEnterBinding
import retrofit2.Call

class EnterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterBinding
    private lateinit var authorizeAPI: AuthorizeAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        authorizeAPI = RetrofitClient.authorizeApi

        toRegister()
        enterCheck()
    }

    private fun enterCheck() {
        binding.enterButton.setOnClickListener {
            val email = binding.enterMail.text.toString()
            val password = binding.enterPassword.text.toString()
            val loginRequest = LoginData(email, password)

            authorizeAPI.login(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: retrofit2.Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        val statusCode = response.code()
                        println("Response code: $statusCode")
                        loginResponse?.let {
                            val role = it.role
                            val userID = it.userId
                            when (statusCode) {
                                200 -> {
                                    when (role) {
                                        "Библиотекарь" -> {
                                            val sharedPreferences = getSharedPreferences("UserLoginData", Context.MODE_PRIVATE)
                                            with(sharedPreferences.edit()) {
                                                putLong("userID", userID!!)
                                                putString("userRole", role)
                                                apply()
                                            }
                                            startActivity(Intent(this@EnterActivity, MainActivity::class.java))
                                        }
                                        "Читатель" -> {
                                            val sharedPreferences = getSharedPreferences("UserLoginData", Context.MODE_PRIVATE)
                                            with(sharedPreferences.edit()) {
                                                putLong("userID", userID!!)
                                                putString("userRole", role)
                                                apply()
                                            }
                                            startActivity(Intent(this@EnterActivity, MainActivity::class.java))
                                        }
                                        else -> {
                                            Toast.makeText(this@EnterActivity, "Неверные данные", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        println("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun toRegister() {
        binding.enterRegisterButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}