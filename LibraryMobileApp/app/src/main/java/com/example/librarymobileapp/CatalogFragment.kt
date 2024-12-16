package com.example.librarymobileapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymobileapp.Adapters.CatalogAdapter
import com.example.librarymobileapp.Database.API.CatalogAPI
import com.example.librarymobileapp.Database.Data.CatalogData
import com.example.librarymobileapp.Database.RetrofitClient
import com.example.librarymobileapp.databinding.FragmentCatalogBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogFragment : Fragment() {
    private lateinit var binding: FragmentCatalogBinding
    private lateinit var catalogApi: CatalogAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogApi = RetrofitClient.catalogApi

        val sharedPreferences =
            requireContext().getSharedPreferences("UserRegisterData", Context.MODE_PRIVATE)
        val libraryId = sharedPreferences.getLong("id", -1)

        fetchBooks(libraryId)
    }

    private fun fetchBooks(libraryId: Long) {
        catalogApi.getAllBooksByLibraryId(libraryId).enqueue(object : Callback<List<CatalogData>> {
            override fun onResponse(
                call: Call<List<CatalogData>>,
                response: Response<List<CatalogData>>
            ) {
                if (response.isSuccessful) {
                    val books = response.body() ?: emptyList()
                    val popularBook = books.maxByOrNull { it.rating }

                    popularBook?.let {
                        savePopularBook(it)
                    }
                    displayBooks(books)
                } else {
                    Log.e("API Error", "Error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CatalogData>>, t: Throwable) {
                Log.e("API Error", t.message ?: "Unknown error")
            }
        })
    }

    private fun savePopularBook(book: CatalogData) {
        val sharedPreferences = requireContext().getSharedPreferences("PopularBook", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("popularBookTitle", book.title)
        editor.putString("popularBookAuthor", book.author)
        editor.putString("popularBookRating", book.rating.toString())
        editor.apply()
    }

    private fun displayBooks(books: List<CatalogData>) {
        val recyclerView = binding.booksCatalog
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CatalogAdapter(books)
    }
}