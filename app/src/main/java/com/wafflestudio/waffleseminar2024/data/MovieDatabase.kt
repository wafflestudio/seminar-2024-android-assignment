package com.wafflestudio.waffleseminar2024.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdConverters {
    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>?): String? {
        return genreIds?.joinToString(",")?.let { "[$it]" } // 리스트를 문자열로 변환하고 대괄호 추가
    }

    @TypeConverter
    fun toGenreIds(genreIdsString: String?): List<Int>? {
        return genreIdsString?.removePrefix("[")?.removeSuffix("]") // 대괄호 제거
            ?.split(",") // 문자열을 쉼표로 분리
            ?.mapNotNull { it.trim().toIntOrNull() } // 각 요소를 Int로 변환, 실패하면 null 반환
    }
}

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(GenreIdConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                    .createFromAsset("database/prepopulated_db.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}