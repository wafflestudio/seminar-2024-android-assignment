package com.wafflestudio.waffleseminar2024.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class], version = 1)
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