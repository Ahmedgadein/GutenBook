package com.ahmedgadein.gutenbook.data.repository

import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBooks(page: Int = 1): Flow<Result<List<Book>>>
    suspend fun getBook(id: Int): Flow<Result<Book>>
}