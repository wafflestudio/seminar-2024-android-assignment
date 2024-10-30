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
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Database(entities = [MyEntity::class, MyEntity2::class], version = 1)
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
    @PrimaryKey val id: Int,
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

@Entity(tableName = "example_table2")
data class MyEntity2(
    val backdrop_path: String?,
    val overview: String?,
    val original_language: String?,
    val original_title: String?,
    val release_date: String?,
    val popularity: Float?,
    val vote_average: Float?,
    @PrimaryKey val id: Int,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val original_country: List<String>?,
    val title: String?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val vote_count: Int?
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

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

    @TypeConverter
    fun fromSpokenLanguagesList(languages: List<SpokenLanguage>?): String? {
        return if (languages == null) null else gson.toJson(languages)
    }

    @TypeConverter
    fun toSpokenLanguagesList(data: String?): List<SpokenLanguage>? {
        return if (data == null) null else {
            val listType = object : TypeToken<List<SpokenLanguage>>() {}.type
            gson.fromJson(data, listType)
        }
    }

    @TypeConverter
    fun fromProductionCompaniesList(companies: List<ProductionCompany>?): String? {
        return if (companies == null) null else gson.toJson(companies)
    }

    @TypeConverter
    fun toProductionCompaniesList(data: String?): List<ProductionCompany>? {
        return if (data == null) null else {
            val listType = object : TypeToken<List<ProductionCompany>>() {}.type
            gson.fromJson(data, listType)
        }
    }

    @TypeConverter
    fun fromProductionCountriesList(countries: List<ProductionCountry>?): String? {
        return if (countries == null) null else gson.toJson(countries)
    }

    @TypeConverter
    fun toProductionCountriesList(data: String?): List<ProductionCountry>? {
        return if (data == null) null else {
            val listType = object : TypeToken<List<ProductionCountry>>() {}.type
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
