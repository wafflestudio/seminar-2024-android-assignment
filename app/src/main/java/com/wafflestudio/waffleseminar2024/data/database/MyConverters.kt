package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wafflestudio.waffleseminar2024.Company
import com.wafflestudio.waffleseminar2024.Country
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.Language

class MyConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromListGenre(value: List<Genre>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListGenre(value: String): List<Genre> {
        val jsonFormValue = value
            .replace("Genre", "")
            .replace("id", "\"id\"")
            .replace("name", "\"name\"")
            .replace("(", "{")
            .replace(")", "}")
            .replace("=", ":")
            .replace(" ","")
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(jsonFormValue, listType)
    }

    @TypeConverter
    fun fromListCompany(value: List<Company>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListCompany(value: String): List<Company> {
        val jsonFormValue = value
            .replace("Company", "")
            .replace("id", "\"id\"")
            .replace("name", "\"name\"")
            .replace("logoPath", "\"logoPath\"")
            .replace("originCountry", "\"originCountry\"")
            .replace("/","")
            .replace("(", "{")
            .replace(")", "}")
            .replace("=", ":")
            .replace(" ","")
        val listType = object : TypeToken<List<Company>>() {}.type
        return gson.fromJson<List<Company>?>(jsonFormValue, listType).map {
            it.copy(logoPath = "/" + it.logoPath)
        }
    }

    @TypeConverter
    fun fromListCountry(value: List<Country>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListCountry(value: String): List<Country>? {
        val jsonFormValue = value
            .replace("Country", "")
            .replace("iso", "\"iso\"")
            .replace("name", "\"name\"")
            .replace("(", "{")
            .replace(")", "}")
            .replace("=", ":")
            .replace(" ","")
        val listType = object : TypeToken<List<Country>>() {}.type
        return gson.fromJson(jsonFormValue, listType)
    }

    @TypeConverter
    fun fromListLanguage(value: List<Language?>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListLanguage(value: String): List<Language> {
        val jsonFormValue = value
            .replace("Language", "")
            .replace("iso_639_1", "\"iso_639_1\"")
            .replace("name", "\"name\"")
            .replace("english_name", "\"english_name\"")
            .replace("(", "{")
            .replace(")", "}")
            .replace("=", ":")
            .replace(" ","")
        val listType = object : TypeToken<List<Language>>() {}.type
        return gson.fromJson(jsonFormValue, listType)
    }
}
