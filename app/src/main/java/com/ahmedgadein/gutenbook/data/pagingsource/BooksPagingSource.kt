package com.ahmedgadein.gutenbook.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.remote.BookService
import com.ahmedgadein.gutenbook.utils.getPage
import retrofit2.await
import kotlin.Exception

class BooksPagingSource(
    private val bookService: BookService
) : PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val pageNumber = params.key ?: 1
        return try {
            val result = bookService.getBooks(pageNumber).await()
            LoadResult.Page(
                data = result.results,
                prevKey = null,
                nextKey = getPage(result.next)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
