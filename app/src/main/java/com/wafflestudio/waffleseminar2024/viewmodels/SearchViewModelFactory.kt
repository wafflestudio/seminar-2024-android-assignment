package com.wafflestudio.waffleseminar2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.database.MovieSearchDatabase

class SearchViewModelFactory(
    private val database: MovieSearchDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
