package com.ahmedgadein.gutenbook.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ahmedgadein.gutenbook.utils.FormatsConverter
import com.ahmedgadein.gutenbook.utils.PersonConverter
import com.ahmedgadein.gutenbook.utils.StringConverter
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(PersonConverter::class, StringConverter::class, FormatsConverter::class)
data class Book(
    @SerializedName("authors")
    val authors: List<Person>,
    @SerializedName("bookshelves")
    val bookshelves: List<String>,
    @SerializedName("copyright")
    val copyright: Boolean,
    @SerializedName("download_count")
    val downloadCount: Int,
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("translators")
    val translators: List<Person>
)
