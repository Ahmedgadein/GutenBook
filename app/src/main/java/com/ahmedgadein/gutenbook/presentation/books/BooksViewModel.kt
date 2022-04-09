package com.ahmedgadein.gutenbook.presentation.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ahmedgadein.gutenbook.data.pagingsource.BooksPagingSource
import com.ahmedgadein.gutenbook.data.remote.BookService
import com.ahmedgadein.gutenbook.domain.AllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val useCase: AllBooksUseCase,
    private val bookService: BookService
) : ViewModel() {
    //    private val _state = MutableStateFlow(BooksUiState())
    val state = Pager(config = PagingConfig(pageSize = 32)) {
        BooksPagingSource(bookService)
    }.flow.cachedIn(viewModelScope)

//    init {
//        viewModelScope.launch {
//            getBooks()
//        }
//    }

//    suspend fun getBooks() {
//        Log.i("Repo", "ViewModel: Called")
//        withContext(CoroutineScope(viewModelScope.coroutineContext).coroutineContext) {
//            useCase().collect { result ->
//                when (result) {
//                    Result.Loading -> _state.update {
//                        it.copy(loading = true)
//                    }
//                    is Result.Error -> {
//                        Log.i("Repo", "Error")
//                        showMessage(result.message)
//                    }
//                    is Result.Success -> {
//                        Log.i("Repo", "Success")
//                        _state.update { it.copy(books = result.value, loading = false) }
//                    }
//                }
//            }
//        }
//    }

//    fun showMessage(content: String) {
//        _state.update {
//            val messages =
//                it.messages + Message(id = UUID.randomUUID().mostSignificantBits, content = content)
//            it.copy(messages = messages, loading = false)
//        }
//    }
//
//    fun messageShown(id: Long) {
//        _state.update {
//            val messages = it.messages.filterNot { it.id == id }
//            it.copy(messages = messages)
//        }
//    }
}
