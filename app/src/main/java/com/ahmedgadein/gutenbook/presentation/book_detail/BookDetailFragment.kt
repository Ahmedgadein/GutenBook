package com.ahmedgadein.gutenbook.presentation.book_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedgadein.gutenbook.adapter.PersonAdapter
import com.ahmedgadein.gutenbook.adapter.StringItemsAdapter
import com.ahmedgadein.gutenbook.databinding.BookDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BookDetailFragment : Fragment() {
    private val viewModel: BookDetailViewModel by viewModels()
    private lateinit var binding: BookDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookDetailFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.collapsingActionBar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false)

        setUI()
        return binding.root
    }

    private fun setUI() {
        val languageAdapter = StringItemsAdapter()
        val bookshelfAdapter = StringItemsAdapter()
        val authorAdapter = PersonAdapter()
        val translatorAdapter = PersonAdapter()

        binding.languagesRecyclerView.apply {
            adapter = languageAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.bookShelveRecyclerView.apply {
            adapter = bookshelfAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.authorRecyclerView.apply {
            adapter = authorAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.translatorRecyclerView.apply {
            adapter = translatorAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getBook(BookDetailFragmentArgs.fromBundle(arguments!!).bookId)
            viewModel.state.collect {
                it.messages.firstOrNull()?.let {
                    showToast(it.content)
                    viewModel.messageShown(it.id)
                }
                binding.bookDetailProgressBar.isVisible = it.loading
                it.book?.let { book ->
                    binding.bookTitle.text = book.title
                    languageAdapter.submitList(book.languages)
                    bookshelfAdapter.submitList(book.bookshelves)
                    authorAdapter.submitList(book.authors)
                    book.translators.let {
                        if (it.isNullOrEmpty())
                            binding.noTranslatorsTextView.isVisible = true
                        translatorAdapter.submitList(it)
                    }
                    binding.book = book

                    binding.saveBookFab.setOnClickListener {
                        viewModel.addBook(book)
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
