package com.wafflestudio.waffleseminar2024.database

import androidx.room.Dao
import androidx.room.Query



@Dao
interface MovieDao {
    @Query("SELECT * FROM example_table")
    suspend fun getAllMovies(): List<Movie>
}

//@Dao
//interface MovieDetailDao {
//    @Query("SELECT * FROM example_table2 WHERE id = :movieId")
//    suspend fun getMovieDetail(movieId: Int): MovieDetail?
//}
