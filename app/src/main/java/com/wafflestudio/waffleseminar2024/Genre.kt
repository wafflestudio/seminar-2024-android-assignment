package com.wafflestudio.waffleseminar2024

data class Genre(
    val id: Int,
    val name: String,
    val iconUrl: String
)

data class GenreResponse(
    val genres: List<Genre>
)

val items = listOf(
    R.drawable.action, R.drawable.adventure,
    R.drawable.animation, R.drawable.comedy,
    R.drawable.crime, R.drawable.documentary,
    R.drawable.drama, R.drawable.family,
    R.drawable.fantasy, R.drawable.history,
    R.drawable.horror, R.drawable.music,
    R.drawable.mystery, R.drawable.romance,
    R.drawable.sf, R.drawable.tv,
    R.drawable.thriller, R.drawable.war,
    R.drawable.western
)
