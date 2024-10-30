package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyEntity(entity: MyEntity)

    @Query("SELECT * FROM example_table")
    fun getAllMyEntities(): List<MyEntity>

    @Query("SELECT * FROM example_table WHERE id = :id")
    fun getMyEntityById(id:Int): MyEntity

    @Query("SELECT * FROM example_table WHERE title LIKE '%' || :titleWord || '%'")
    fun getMoviesByTitle(titleWord: String): List<MyEntity>

    @Query("SELECT * FROM example_table WHERE genre_ids LIKE '%' || :genreId || '%'")
    fun getMoviesByGenre(genreId: Int): List<MyEntity>
}