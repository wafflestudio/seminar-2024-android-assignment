package com.wafflestudio.waffleseminar2024.database

import androidx.room.Dao
import androidx.room.Query



@Dao
interface MovieDao {
    @Query("SELECT * FROM example_table")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM example_table WHERE title LIKE '%' || :query || '%'")
    suspend fun getMoviesByQuery(query: String): List<Movie>

    @Query("SELECT * FROM example_table WHERE :genreId = -1 OR genre_ids LIKE '%' || :genreId || '%'")
    suspend fun getMoviesByGenre(genreId: Int): List<Movie>


}


@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM example_table2 WHERE id = :id")
    suspend fun getMovieDetailById(id: Int): MovieDetail?
}

//@Dao
//interface MovieDetailDao {
//    @Query("SELECT * FROM example_table2 WHERE id = :movieId")
//    suspend fun getMovieDetail(movieId: Int): MovieDetail?
//}