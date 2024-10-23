package com.wafflestudio.waffleseminar2024.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example_table")
data class Movie(
    @PrimaryKey val id: Int?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?, // TypeConverter 사용 없이 문자열로 저장
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?
)

@Entity(tableName = "example_table2")
data class MovieDetail(
    @PrimaryKey val id: Int,
    val budget: Int,
    val revenue: Int,
    val runtime: Int,
    val genres: List<Genre>,  // TypeConverter로 변환 필요
    val status: String
)

data class Genre(
    val id: Int,
    val name: String
)
