package com.wafflestudio.waffleseminar2024.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [SearchQuery::class], version = 2)
abstract class MovieSearchDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao

    companion object {
        @Volatile
        private var INSTANCE: MovieSearchDatabase? = null

        fun getInstance(context: Context): MovieSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieSearchDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()  // 버전 충돌 시 데이터베이스 초기화
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
