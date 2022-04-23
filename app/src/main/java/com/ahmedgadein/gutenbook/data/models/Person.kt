package com.ahmedgadein.gutenbook.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Person(
    @SerializedName("birth_year")
    val birthYear: Int,
    @SerializedName("death_year")
    val deathYear: Int,
    @SerializedName("name")
    val name: String
)
