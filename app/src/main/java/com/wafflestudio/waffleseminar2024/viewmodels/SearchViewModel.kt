package com.wafflestudio.waffleseminar2024.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.database.MovieSearchDatabase
import com.wafflestudio.waffleseminar2024.database.SearchQuery
import kotlinx.coroutines.launch

class SearchViewModel(private val database: MovieSearchDatabase) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchHistory = MutableLiveData<List<SearchQuery>>()
    val searchHistory: LiveData<List<SearchQuery>> = _searchHistory

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        saveQueryToDatabase(query)
    }

    private fun saveQueryToDatabase(query: String) {
        viewModelScope.launch {
            val searchQuery = SearchQuery(query = query)
            database.searchQueryDao().insertQuery(searchQuery)
        }
    }

    fun deleteSearchQuery(query: SearchQuery) {
        viewModelScope.launch {
            database.searchQueryDao().deleteQuery(query)
            loadSearchHistory()  // 삭제 후 목록 갱신

        }
    }
    fun loadSearchHistory() {
        viewModelScope.launch {
            val history = database.searchQueryDao().getAllQueries()
            _searchHistory.value = history
        }
    }
}
