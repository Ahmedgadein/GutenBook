package com.ahmedgadein.gutenbook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedgadein.gutenbook.data.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("Select * from book where id = :id")
    fun getBook(id: Int): Book

    @Query("select * from book")
    fun getAllBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(book: Book)
}
