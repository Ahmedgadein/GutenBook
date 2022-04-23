package com.ahmedgadein.gutenbook.presentation.book_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedgadein.gutenbook.data.local.BookDao
import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.Message
import com.ahmedgadein.gutenbook.data.models.Result
import com.ahmedgadein.gutenbook.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val repository: BookRepository,
    private val dao: BookDao
) :
    ViewModel() {

    private val _state = MutableStateFlow(BookDetailUiState())
    val state = _state.asStateFlow()

    suspend fun getBook(id: Int) {
        withContext(viewModelScope.coroutineContext) {
            repository.getBook(id).collect { bookResult ->
                when (bookResult) {
                    Result.Loading -> _state.update {
                        it.copy(loading = true)
                    }
                    is Result.Error -> showMessage(bookResult.message)
                    is Result.Success -> _state.update {
                        it.copy(loading = false, book = bookResult.value)
                    }
                }
            }
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.upsert(book)
                showMessage("Book added Successfully")
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
