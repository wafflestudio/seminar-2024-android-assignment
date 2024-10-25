package com.wafflestudio.waffleseminar2024.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_queries")
data class SearchQuery (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // 자동 증가하는 기본 키
    val query: String
)