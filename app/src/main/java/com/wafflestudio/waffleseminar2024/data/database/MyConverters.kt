package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.TypeConverter

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
}
