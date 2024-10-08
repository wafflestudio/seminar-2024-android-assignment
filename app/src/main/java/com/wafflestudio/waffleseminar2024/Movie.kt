package com.wafflestudio.waffleseminar2024
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Movie(
    val id: Int,
    val genre_ids: List<Int>,
    val poster_path: String,
    val title: String
)

@Serializable
data class MovieResponse(
    val movies: List<Movie>
)