package com.wafflestudio.waffleseminar2024.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromIntList(value: List<Int>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int>? {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }

//
//    @TypeConverter
//    fun fromGenreList(genres: List<Genre>?): String? {
//        return gson.toJson(genres)
//    }
//
//    @TypeConverter
//    fun toGenreList(data: String?): List<Genre>? {
//        val listType = object : TypeToken<List<Genre>>() {}.type
//        return gson.fromJson(data, listType)
//    }
//
//    @TypeConverter
//    fun fromStringList(strings: List<String>?): String? {
//        return gson.toJson(strings)
//    }
//
//    @TypeConverter
//    fun toStringList(data: String?): List<String>? {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return gson.fromJson(data, listType)
//    }
//
//    @TypeConverter
//    fun fromProductionCompanyList(companies: List<ProductionCompany>?): String? {
//        return gson.toJson(companies)
//    }
//
//    @TypeConverter
//    fun toProductionCompanyList(data: String?): List<ProductionCompany>? {
//        val listType = object : TypeToken<List<ProductionCompany>>() {}.type
//        return gson.fromJson(data, listType)
//    }
//
//    @TypeConverter
//    fun fromProductionCountryList(countries: List<ProductionCountry>?): String? {
//        return gson.toJson(countries)
//    }
//
//    @TypeConverter
//    fun toProductionCountryList(data: String?): List<ProductionCountry>? {
//        val listType = object : TypeToken<List<ProductionCountry>>() {}.type
//        return gson.fromJson(data, listType)
//    }

    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(data: String?): List<Genre>? {
        if (data == null) return emptyList()
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        if (data == null) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }
}