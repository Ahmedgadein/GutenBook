package com.ahmedgadein.gutenbook.presentation.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedgadein.gutenbook.adapter.BookAdapter
import com.ahmedgadein.gutenbook.databinding.BooksFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BooksFragment : Fragment() {
    private val viewModel: BooksViewModel by viewModels()
    lateinit var binding: BooksFragmentBinding
    lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BooksFragmentBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        bookAdapter = BookAdapter()
        binding.booksRecyclerView.apply {
            adapter = bookAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                binding.progressBar.isVisible = it.loading
                it.messages.firstOrNull()?.let {
                    showSnackbar(it.content)
                    viewModel.messageShown(it.id)
                }
                bookAdapter.submitList(it.books)
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}