package com.ahmedgadein.gutenbook.presentation.book_detail

import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.Message

data class BookDetailUiState(
    val loading: Boolean = false,
    val messages: List<Message> = emptyList(),
    val book: Book? = null
)
