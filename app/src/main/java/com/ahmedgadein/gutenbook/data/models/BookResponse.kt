package com.ahmedgadein.gutenbook.data.models


import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Book>
)