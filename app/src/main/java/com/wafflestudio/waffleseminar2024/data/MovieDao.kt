package com.wafflestudio.waffleseminar2024.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(entity: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity

}