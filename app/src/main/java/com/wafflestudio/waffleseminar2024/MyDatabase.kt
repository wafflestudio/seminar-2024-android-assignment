package com.wafflestudio.waffleseminar2024

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Database(entities = [MyEntity::class, MyEntity2::class], version = 2)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "example_database"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/prepopulated_db.db") // 이 부분을 추가합니다.
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}

@Dao
interface  MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyEntity(entity: MyEntity)

    @Query("SELECT * FROM example_table")
    fun getAllMyEntities(): LiveData<List<MyEntity>>

    @Query("SELECT * FROM example_table WHERE id = :id")
    fun getMyEntityById(id: Int): MyEntity

    @Query("SELECT * FROM example_table2 WHERE id = :id")
    fun getMovieDetailById(id: Int): MyEntity2
}

@Entity(tableName = "example_table")
//@TypeConverters(Converters::class)
data class MyEntity(
    @PrimaryKey val id: Int?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Float?,
    val vote_count: Int?
)
data class Company(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val origin_country: String
)

data class Country(
    val iso_3166_1: String,
    val name: String
)

data class Language(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

@Entity(tableName = "example_table2")
@TypeConverters(Converters2::class)
data class MyEntity2(
    @PrimaryKey val id: Int?,
    val title: String?,
    val original_title: String?,
    val backdrop_path: String?,
    val budget: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val vote_average: Double?,
    val genres: List<Genre>?,
    val homepage: String?,
    val original_language: String?,
    val popularity: Float?,
    val production_companies: List<Company>?,
    val production_countries: List<Country>?,
    val spoken_languages: List<Language>?,
    val tagline: String?,
    val vote_count: Int?
)

class Converters2 {
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
    fun toListCountry(value: String): List<Country> {
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

@Entity(tableName = "example_table")
@TypeConverters(Converters::class)
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenresList(genres: List<Genre>?): String? {
        return if (genres == null) null else gson.toJson(genres)
    }

    @TypeConverter
    fun toGenresList(genres: String?): List<Genre>? {
        return genres?.let { Json.decodeFromString(it) }
    }

    @TypeConverter
    fun fromIntegerList(integers: List<Int>?): String? {
        return if (integers == null) null else gson.toJson(integers)
    }

    @TypeConverter
    fun toIntegerList(data: String?): List<Int>? {
        return if (data == null) null else {
            val listType = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(data, listType)
        }
    }

    @TypeConverter
    fun fromStringList(strings: List<String>?): String? {
        return if (strings == null) null else gson.toJson(strings)
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        return if (data == null) null else {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(data, listType)
        }
    }
}


/*
class Converters {
    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>?): String? {
        return genreIds?.joinToString(",")
    }

    @TypeConverter
    fun toGenreIds(genreIds: String?): List<Int>? {
        return genreIds?.split(",")?.map { it.toInt() }
    }
}

 */
