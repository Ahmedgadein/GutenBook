package com.ahmedgadein.gutenbook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedgadein.gutenbook.common.DATABASE_NAME
import com.ahmedgadein.gutenbook.data.models.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class GutenBookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: GutenBookDatabase? = null

        fun getInstance(context: Context): GutenBookDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GutenBookDatabase {
            return Room.databaseBuilder(context, GutenBookDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
