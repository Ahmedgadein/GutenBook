package com.ahmedgadein.gutenbook.domain

import com.ahmedgadein.gutenbook.data.repository.BookRepository
import javax.inject.Inject

class AllBooksUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke() = repository.getBooks()
}