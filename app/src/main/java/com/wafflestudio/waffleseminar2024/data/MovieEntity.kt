package com.wafflestudio.waffleseminar2024.data

import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@Entity(tableName = "example_table2")
@TypeConverters(GenreListConverter::class)
data class MovieEntity(
    @PrimaryKey val id: Int?,
    val backdrop_path: String?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val production_companies: String?,
    val production_countries: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: String?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val vote_average: Float?,
    val vote_count: Int?
)
