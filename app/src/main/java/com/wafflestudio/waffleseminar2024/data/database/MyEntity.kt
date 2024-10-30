package com.wafflestudio.waffleseminar2024.data.database

import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "example_table")
@TypeConverters(MyConverters::class)
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
    val vote_count: Int?,
)