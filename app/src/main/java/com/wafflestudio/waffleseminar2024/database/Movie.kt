package com.wafflestudio.waffleseminar2024.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

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
    @PrimaryKey val id: Int?,  // PrimaryKey는 nullable 허용
    val backdrop_path: String?,
    val budget: Int?,
    val genres: String?,  // JSON 문자열로 저장
    val homepage: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: String?,  // JSON 문자열로 저장
    val production_countries: String?,  // JSON 문자열로 저장
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: String?,  // JSON 문자열로 저장
    val status: String?,
    val tagline: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?
)


data class Genre(
    val id: Int,
    val name: String
)


// ProductionCompany 클래스 정의
data class ProductionCompany(val id: Int, val name: String, val origin_country: String)

// ProductionCountry 클래스 정의
data class ProductionCountry(val name: String, val origin_country: String)