package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wafflestudio.waffleseminar2024.Genre

class MyConverters {
    @TypeConverter
    fun fromListIntToString(intList: List<Int>): String {
        return intList.joinToString(",") // List<Int>를 String으로 변환
    }

    @TypeConverter
    fun toListIntFromString(stringList: String): List<Int> {
        val result = mutableListOf<Int>()
        val split = stringList.split(",")

        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: NumberFormatException) {
            }
        }
        return result
    }

    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun stringToBoolean(value: String?): Boolean? {
        return value?.toBoolean()
    }

    @TypeConverter
    fun booleanToString(value: Boolean?): String? {
        return value?.toString()
    }
}
