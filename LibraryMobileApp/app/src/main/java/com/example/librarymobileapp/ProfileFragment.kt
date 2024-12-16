package com.example.librarymobileapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.librarymobileapp.Database.Data.ProfileData
import com.example.librarymobileapp.Database.RetrofitClient
import com.example.librarymobileapp.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUser()
    }

    private fun getUser() {
        val sharedPreferences = requireContext().getSharedPreferences("UserLoginData", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getLong("userID", -1)

        if (userId != -1L) {
            RetrofitClient.profileApi.getUserById(userId).enqueue(object : Callback<ProfileData> {
                override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                    if (response.isSuccessful) {
                        val user = response.body()

                        binding.profileName.text = "Имя: ${user?.name.toString()}"
                        binding.profileEmail.text = "Почта: ${ user?.email.toString() }"
                        binding.profileRegistrationDate.text = "Дата регистрации: ${user?.date.toString()}"
                        binding.profileStatus.text = "Статус: ${user?.status.toString()}"

                    } else {
                        Toast.makeText(requireContext(), "Ошибка: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                    Toast.makeText(requireContext(), "Ошибка: ${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}