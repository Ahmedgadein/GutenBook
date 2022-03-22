package com.ahmedgadein.gutenbook.presentation.savedbooks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedgadein.gutenbook.R

class SavedBooks : Fragment() {

    companion object {
        fun newInstance() = SavedBooks()
    }

    private lateinit var viewModel: SavedBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_books_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SavedBooksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}