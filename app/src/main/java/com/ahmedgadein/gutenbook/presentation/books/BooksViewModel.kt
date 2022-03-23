package com.ahmedgadein.gutenbook.presentation.books

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedgadein.gutenbook.data.models.Message
import com.ahmedgadein.gutenbook.data.models.Result
import com.ahmedgadein.gutenbook.domain.AllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val useCase: AllBooksUseCase) : ViewModel() {
    private val _state = MutableStateFlow(BooksUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getBooks()
        }
    }

    suspend fun getBooks() {
        Log.i("Repo", "ViewModel: Called")
        withContext(CoroutineScope(viewModelScope.coroutineContext).coroutineContext) {
            useCase().collect { result ->
                when (result) {
                    Result.Loading -> _state.update {
                        it.copy(loading = true)
                    }
                    is Result.Error -> {
                        Log.i("Repo", "Error")
                        showMessage(result.message)
                    }
                    is Result.Success -> {
                        Log.i("Repo", "Success")
                        _state.update { it.copy(books = result.value, loading = false) }
                    }
                }
            }
        }
    }

    fun showMessage(content: String) {
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