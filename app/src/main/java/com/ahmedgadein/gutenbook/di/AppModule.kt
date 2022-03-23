package com.ahmedgadein.gutenbook.di

import com.ahmedgadein.gutenbook.data.remote.BookService
import com.ahmedgadein.gutenbook.data.repository.BookRepository
import com.ahmedgadein.gutenbook.data.repository.BookRepositoryImpl
import com.ahmedgadein.gutenbook.domain.AllBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideApiService(): BookService = BookService.invoke()

    @Provides
    fun provideBookRepository(
        ioDispatcher: CoroutineDispatcher,
        service: BookService
    ): BookRepository =
        BookRepositoryImpl(service, ioDispatcher)

    @Provides
    fun provideAllBooksUseCase(repository: BookRepository): AllBooksUseCase =
        AllBooksUseCase(repository)
}