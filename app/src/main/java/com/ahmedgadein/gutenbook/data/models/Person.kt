package com.ahmedgadein.gutenbook.data.models


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("birth_year")
    val birthYear: Int,
    @SerializedName("death_year")
    val deathYear: Int,
    @SerializedName("name")
    val name: String
)