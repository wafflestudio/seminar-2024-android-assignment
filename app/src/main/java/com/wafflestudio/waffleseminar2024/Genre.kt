package com.wafflestudio.waffleseminar2024

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Genre (
    val id: Int,
    val name: String
)