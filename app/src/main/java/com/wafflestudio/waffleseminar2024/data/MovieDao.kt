package com.wafflestudio.waffleseminar2024.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entity: MovieEntity)

    @Query("SELECT * FROM example_table")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM example_table WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM example_table WHERE title LIKE '%' || :titleQuery || '%'")
    suspend fun getMovieByTitleQuery(titleQuery: String): List<MovieEntity>

}

