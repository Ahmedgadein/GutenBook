package com.ahmedgadein.gutenbook.presentation.books

import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.Message

data class BooksUiState(
    val loading: Boolean = false,
    val messages: List<Message> = emptyList(),
    val books: List<Book> = emptyList()
)