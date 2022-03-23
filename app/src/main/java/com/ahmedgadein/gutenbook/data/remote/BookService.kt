package com.ahmedgadein.gutenbook.data.remote

import com.ahmedgadein.gutenbook.data.models.Book
import com.ahmedgadein.gutenbook.data.models.BookResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("books/")
    fun getBooks(@Query("page") page: Int): Call<BookResponse>

    @GET("books/{id}")
    fun getBook(@Path("id") id: Int): Call<Book>

    companion object {
        operator fun invoke(): BookService {
            return Retrofit.Builder()
                .baseUrl("https://gutendex.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookService::class.java)
        }
    }
}