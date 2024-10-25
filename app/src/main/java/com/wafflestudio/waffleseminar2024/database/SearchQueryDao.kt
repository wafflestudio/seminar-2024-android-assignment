package com.wafflestudio.waffleseminar2024.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SearchQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(searchQuery: SearchQuery)

    @Query("SELECT * FROM search_queries ORDER BY id DESC")
    suspend fun getAllQueries(): List<SearchQuery>

    @Delete
    suspend fun deleteQuery(searchQuery: SearchQuery)
}