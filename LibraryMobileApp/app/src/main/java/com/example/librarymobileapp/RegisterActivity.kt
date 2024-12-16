package com.example.librarymobileapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.librarymobileapp.Database.API.LibraryAPI
import com.example.librarymobileapp.Database.RetrofitClient
import com.example.librarymobileapp.Database.API.AuthorizeAPI
import com.example.librarymobileapp.Adapters.LibraryAdapter
import com.example.librarymobileapp.Database.Data.LibraryData
import com.example.librarymobileapp.Database.Data.LibraryID
import com.example.librarymobileapp.Database.Data.RegisterData
import com.example.librarymobileapp.databinding.ActivityRegisterBinding
import com.example.librarymobileapp.databinding.FragmentDialogWindowBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dialogBinding: FragmentDialogWindowBinding
    private lateinit var authorizeAPi: AuthorizeAPI
    private lateinit var libraryAPI: LibraryAPI
    private lateinit var adapter: LibraryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        authorizeAPi = RetrofitClient.authorizeApi
        libraryAPI = RetrofitClient.libraryApi

        goEnter()
        selectLibrary()
        registerUser()
    }

    private fun registerUser() {

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dateFormat.format(calendar.time)

        binding.registerButton.setOnClickListener {
            if (binding.registerName.text.isNotEmpty() && binding.registerMail.text.isNotEmpty() &&
                binding.registerPassword.text.isNotEmpty()
            ) {

                val name = binding.registerName.text.toString()
                val email = binding.registerMail.text.toString()
                val password = binding.registerPassword.text.toString()
                val date = formattedDate.toString()
                val id = binding.libraryIDRegister.text.toString().toLong()
                val nameLibrary = binding.libraryNameRegister.text.toString()
                val addressLibrary = binding.libraryAddressRegister.text.toString()
                val library = LibraryData(name = nameLibrary, address = addressLibrary)

                val sharedPreferences = getSharedPreferences("UserRegisterData", Context.MODE_PRIVATE)
                sharedPreferences.edit()
                    .putLong("id", id)
                    .apply()


                val registerRequest = RegisterData(
                    name = name,
                    email = email,
                    password = password,
                    date = date,
                    status = "Активен",
                    role = "Читатель",
                    library = library
                )

                authorizeAPi.register(registerRequest).enqueue(object : retrofit2.Callback<Int> {
                    override fun onResponse(call: Call<Int>, response: retrofit2.Response<Int>) {
                        if (response.isSuccessful) {
                            val statusCode = response.body() ?: 200
                            when (statusCode) {
                                201 -> {
                                    startActivity(
                                        Intent(
                                            this@RegisterActivity,
                                            EnterActivity::class.java
                                        )
                                    )
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Пользователь успешно создан",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                409 -> Toast.makeText(
                                    this@RegisterActivity,
                                    "User  already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Error: ${response.code()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            } else {
                Toast.makeText(
                    this@RegisterActivity,
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }

    private fun selectLibrary() {
        binding.selectLibraryButton.setOnClickListener {
            val dialog = Dialog(this)
            dialogBinding = FragmentDialogWindowBinding.inflate(LayoutInflater.from(this))
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            fetchLibraries(dialogBinding.libraryList, dialog)
            dialog.show()
        }
    }

    private fun fetchLibraries(listView: ListView, dialog: Dialog) {
        libraryAPI.getLibraries().enqueue(object : Callback<List<LibraryID>> {
            override fun onResponse(
                call: Call<List<LibraryID>>,
                response: Response<List<LibraryID>>
            ) {

                if (response.isSuccessful) {
                    val libraries = response.body() ?: emptyList()
                    adapter = LibraryAdapter(this@RegisterActivity, libraries)
                    listView.adapter = adapter

                    listView.setOnItemClickListener { parent, view, position, id ->
                        val selectedLibrary = libraries[position]
                        binding.libraryIDRegister.setText(selectedLibrary.id.toString())
                        binding.libraryNameRegister.setText(selectedLibrary.name)
                        binding.libraryAddressRegister.setText(selectedLibrary.address)
                        dialog.dismiss()
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Ошибка получения данных",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<LibraryID>>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Ошибка: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun goEnter() {
        binding.registerEnterButton.setOnClickListener {
            finish()
        }
    }
}