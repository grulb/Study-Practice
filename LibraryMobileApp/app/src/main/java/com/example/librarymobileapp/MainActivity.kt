package com.example.librarymobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.librarymobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        changeFragment()
        setFragmentTransaction(CatalogFragment())
    }

    private fun changeFragment() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.catalogFragmentItem -> {
                    setFragmentTransaction(CatalogFragment())
                    binding.frameHeader.text = "Каталог"
                }
                R.id.bestsellersFragmentItem -> {
                    setFragmentTransaction(BestsellerFragment())
                    binding.frameHeader.text = "Бестселлер"
                }
                R.id.profileFragmentItem -> {
                    setFragmentTransaction(ProfileFragment())
                    binding.frameHeader.text = "Профиль"
                }
            }
            true
        }

        binding.instructionButton.setOnClickListener {
            startActivity(Intent(this, InstructionActivity::class.java))
        }
    }

    private fun setFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, fragment)
            commit()
        }
    }
}