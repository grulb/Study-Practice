package com.example.librarymobileapp.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.librarymobileapp.Database.Data.LibraryData
import com.example.librarymobileapp.Database.Data.LibraryID
import com.example.librarymobileapp.R

class LibraryAdapter(context: Context, private val libraries: List<LibraryID>) :
    ArrayAdapter<LibraryID>(context, 0, libraries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val library = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.library_item, parent, false)

        val idTextView = view.findViewById<TextView>(R.id.libraryIdTextView)
        val nameTextView = view.findViewById<TextView>(R.id.libraryNameTextView)
        val addressTextView = view.findViewById<TextView>(R.id.libraryAddressTextView)

        idTextView.text = library?.id.toString()
        nameTextView.text = library?.name
        addressTextView.text = library?.address

        idTextView.setTextColor(Color.BLACK)
        nameTextView.setTextColor(Color.BLACK)
        addressTextView.setTextColor(Color.BLACK)

        return view
    }
}