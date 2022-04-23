package com.ahmedgadein.gutenbook.utils

import androidx.room.TypeConverter
import com.ahmedgadein.gutenbook.data.models.Formats
import com.ahmedgadein.gutenbook.data.models.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PersonConverter {
    @TypeConverter
    fun peopleToString(people: List<Person>): List<String> =
        people.map { Gson().toJson(it) }.toList()

    @TypeConverter
    fun stringToPeople(people: List<String>): List<Person> =
        people.map { Gson().fromJson(it, Person::class.java) }.toList()
}

class StringConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

class FormatsConverter {
    @TypeConverter
    fun formatsToString(formats: Formats): String =
        Gson().toJson(formats)

    @TypeConverter
    fun stringToFormats(formats: String): Formats =
        Gson().fromJson(formats, Formats::class.java)
}
