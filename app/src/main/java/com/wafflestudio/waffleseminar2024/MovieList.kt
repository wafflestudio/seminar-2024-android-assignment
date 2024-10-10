package com.wafflestudio.waffleseminar2024

import kotlinx.serialization.Serializable

@Serializable
data class MovieList(
    val movies: List<Movie>
)