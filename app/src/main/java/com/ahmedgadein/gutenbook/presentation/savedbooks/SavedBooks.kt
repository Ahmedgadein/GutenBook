package com.ahmedgadein.gutenbook.presentation.savedbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedgadein.gutenbook.adapter.SavedBooksAdapter
import com.ahmedgadein.gutenbook.databinding.SavedBooksFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedBooks : Fragment() {
    private val viewModel: SavedBooksViewModel by viewModels()
    lateinit var binding: SavedBooksFragmentBinding
    lateinit var bookAdapter: SavedBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SavedBooksFragmentBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        bookAdapter = SavedBooksAdapter()
        binding.savedBooksRecyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = bookAdapter
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                binding.emptySavedBooks.isVisible = it.books.isNullOrEmpty()

                it.messages.firstOrNull()?.let {
                    showSnackbar(it.content)
                    viewModel.messageShown(it.id)
                }
                bookAdapter.submitList(it.books)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadSavedBooks()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
