package com.wafflestudio.waffleseminar2024.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MovieDetail::class], version = 1)
@TypeConverters(Converters2::class)
abstract class MovieDetailDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDetailDatabase? = null

        fun getInstance(context: Context): MovieDetailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDetailDatabase::class.java,
                    "movie_detail_database"
                ).fallbackToDestructiveMigration()
                    .createFromAsset("database/prepopulated_db.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
