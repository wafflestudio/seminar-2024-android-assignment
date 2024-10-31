package com.wafflestudio.waffleseminar2024.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Genre(val id: Int, val name: String)

class GenreListConverter {

    // List<Genre>를 String으로 변환하여 데이터베이스에 저장
    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return genres?.joinToString(",") { genre -> "Genre(id=${genre.id}, name=${genre.name})" }
    }

    // 저장된 String을 List<Genre>로 변환하여 불러오기
    @TypeConverter
    fun toGenreList(genreString: String?): List<Genre>? {
        return genreString
            ?.split("Genre(")  // "Genre("를 기준으로 분리
            ?.filter { it.isNotBlank() }
            ?.mapNotNull { genrePart ->
                val id = genrePart.substringAfter("id=").substringBefore(",").toIntOrNull()
                val name = genrePart.substringAfter("name=").substringBefore(")")
                if (id != null) Genre(id, name) else null
            }
    }
}

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(GenreListConverter::class)
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
                    "movie_database2"
                )
                    .createFromAsset("database/prepopulated_db.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}