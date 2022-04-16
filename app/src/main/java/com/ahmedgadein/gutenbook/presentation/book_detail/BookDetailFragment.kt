package com.ahmedgadein.gutenbook.presentation.book_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                it.messages.firstOrNull()?.let {}
                binding.bookDetailProgressBar.isVisible = it.loading
                it.book?.let {
                    binding.bookTitle.text = it.title
                    languageAdapter.submitList(it.languages)
                    bookshelfAdapter.submitList(it.bookshelves)
                    authorAdapter.submitList(it.authors)
                    it.translators.let {
                        if (it.isNullOrEmpty())
                            binding.noTranslatorsTextView.isVisible = true
                        translatorAdapter.submitList(it)
                    }
                    binding.book = it
                }
            }
        }
    }
}
