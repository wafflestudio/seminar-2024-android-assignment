package com.wafflestudio.waffleseminar2024

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val original_title: String,
    val backdrop_path: String?,
    val budget: Int,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int,
    val runtime: Int?,
    val status: String?,
    val vote_average: Double,
    val genres: List<Genre>?
) : Parcelable

data class Company(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String?
)

data class Country(
    val iso: String,
    val name: String
)

data class Language(
    val iso_639_1: String,
    val name: String,
    val english_name: String
)


const val GenreListString = """
    [
    {
      "id": 28,
      "name": "액션"
    },
    {
      "id": 12,
      "name": "모험"
    },
    {
      "id": 16,
      "name": "애니메이션"
    },
    {
      "id": 35,
      "name": "코미디"
    },
    {
      "id": 80,
      "name": "범죄"
    },
    {
      "id": 99,
      "name": "다큐멘터리"
    },
    {
      "id": 18,
      "name": "드라마"
    },
    {
      "id": 10751,
      "name": "가족"
    },
    {
      "id": 14,
      "name": "판타지"
    },
    {
      "id": 36,
      "name": "역사"
    },
    {
      "id": 27,
      "name": "공포"
    },
    {
      "id": 10402,
      "name": "음악"
    },
    {
      "id": 9648,
      "name": "미스터리"
    },
    {
      "id": 10749,
      "name": "로맨스"
    },
    {
      "id": 878,
      "name": "SF"
    },
    {
      "id": 10770,
      "name": "TV 영화"
    },
    {
      "id": 53,
      "name": "스릴러"
    },
    {
      "id": 10752,
      "name": "전쟁"
    },
    {
      "id": 37,
      "name": "서부"
    }
  ]
"""


@Serializable
@Parcelize

class Genre(
    val id: Int,
    val name: String,
) : Parcelable

fun parseGenreList(jsonString: String): List<Genre> {
    return Json.decodeFromString(jsonString)
}

val GenreList: List<Genre> = parseGenreList(GenreListString)
