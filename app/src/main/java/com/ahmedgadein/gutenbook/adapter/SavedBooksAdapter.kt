package com.ahmedgadein.gutenbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedgadein.gutenbook.R
import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.databinding.BookItemListBinding
import com.ahmedgadein.gutenbook.presentation.books.BooksFragmentDirections
import com.ahmedgadein.gutenbook.presentation.savedbooks.SavedBooksDirections
import com.bumptech.glide.Glide

class SavedBooksAdapter : ListAdapter<Book, RecyclerView.ViewHolder>(BookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            BookItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = getItem(position)
        (holder as BookViewHolder).bind(book)
    }

    private class BookViewHolder(private val binding: BookItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var book: Book? = null

        init {
            itemView.setOnClickListener {
                navigateToDetails()
            }
        }

        private fun navigateToDetails() {
            book?.let {
                itemView.findNavController()
                    .navigate(SavedBooksDirections.actionSavedBooksToBookDetailFragment(it.id, fromSavedBooks = true))
            }
        }

        fun bind(book: Book?) {
            this.book = book
            book?.let {
                binding.bookName.text = book.title
                Glide.with(itemView)
                    .load(book.formats.imagejpeg)
                    .placeholder(R.drawable.ic_book_placeholder)
                    .fitCenter()
                    .into(binding.bookPhoto)
            }
        }
    }
}