package com.example.librarymobileapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymobileapp.databinding.FragmentBestsellerBinding
import com.example.librarymobileapp.databinding.FragmentProfileBinding

class BestsellerFragment : Fragment() {
    private lateinit var binding: FragmentBestsellerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBestsellerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        val sharedPreferences = requireContext().getSharedPreferences("PopularBook", Context.MODE_PRIVATE)
        val popularTitle = sharedPreferences.getString("popularBookTitle", "Empty")
        val popularAuthor = sharedPreferences.getString("popularBookAuthor", "Empty")
        val popularRating = sharedPreferences.getString("popularBookRating", "Empty")

        binding.popularTitle.text = popularTitle
        binding.popularAuthor.text = popularAuthor
        binding.popularRating.text = popularRating
    }
}