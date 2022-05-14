package com.ahmedgadein.gutenbook.presentation.savedbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedgadein.gutenbook.data.models.Message
import com.ahmedgadein.gutenbook.data.repository.BookRepository
import com.ahmedgadein.gutenbook.presentation.BooksUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SavedBooksViewModel @Inject constructor(private val repository: BookRepository) :
    ViewModel() {

    init {
        loadSavedBooks()
    }

    private val _state = MutableStateFlow(BooksUiState())
    val state = _state.asStateFlow()

    private fun loadSavedBooks() {
        viewModelScope.launch {
            repository.getSavedBooks().collect { result ->
                result.let { books ->
                    if (!books.isNullOrEmpty()) {
                        _state.update { it.copy(books = books) }
                    }
                }
            }
        }
    }

    private fun showMessage(content: String) {
        _state.update {
            val messages =
                it.messages + Message(id = UUID.randomUUID().mostSignificantBits, content = content)
            it.copy(messages = messages, loading = false)
        }
    }

    fun messageShown(id: Long) {
        _state.update {
            val messages = it.messages.filterNot { it.id == id }
            it.copy(messages = messages)
        }
    }
}
