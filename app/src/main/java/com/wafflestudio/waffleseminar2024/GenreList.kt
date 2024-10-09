package com.wafflestudio.waffleseminar2024

import kotlinx.serialization.Serializable

@Serializable
data class GenreList(
    val genres: List<Genre>
)