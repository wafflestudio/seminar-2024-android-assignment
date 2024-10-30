package com.wafflestudio.waffleseminar2024

import android.content.Context
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

@Database(entities = [MyEntity::class], version = 1)
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
    fun getAllMyEntities(): List<MyEntity>

    @Query("SELECT * FROM example_table WHERE id = :id")
    fun getMyEntityById(id: Int): MyEntity
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
