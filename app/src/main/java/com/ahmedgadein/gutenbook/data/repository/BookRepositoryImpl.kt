package com.ahmedgadein.gutenbook.data.repository

import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.Result
import com.ahmedgadein.gutenbook.data.remote.BookService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val service: BookService,
    private val ioDispatcher: CoroutineDispatcher
) :
    BookRepository {
    override suspend fun getBooks(page: Int): Flow<Result<List<Book>>> = flow {
        emit(Result.Loading)
        try {
            val response = service.getBooks(page).await()
            emit(Result.Success(response.results))
        } catch (exception: Exception) {
            emit(Result.Error(exception.toString()))
        }
    }

    override suspend fun getBook(id: Int): Flow<Result<Book>> = flow {
        emit(Result.Loading)
        try {
            val response = service.getBook(id).await()
            emit(Result.Success(response))
        } catch (exception: Exception) {
            emit(Result.Error(exception.toString()))
        }
    }
}
