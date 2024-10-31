package com.wafflestudio.waffleseminar2024

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EntityViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: MyDao = MyDatabase.getDatabase(application).myDao()

    private val _movieEntities = MutableLiveData<List<MyEntity>>()
    val movieEntities: LiveData<List<MyEntity>> get() = _movieEntities

    init {
        loadAllMovies()
    }

    private fun loadAllMovies() {
        viewModelScope.launch {
            val entities = dao.getAllMyEntities()
            Log.d("EntityViewModel", "Loaded entities from database: $entities") // 로그 추가
            _movieEntities.postValue(entities ?: emptyList())
        }
    }

    fun genreQuery(genreId: Int): List<MyEntity> {
        return _movieEntities.value?.filter { it.genre_ids?.contains(genreId) == true } ?: emptyList()
    }

    fun titleQuery(titleWord: String): List<MyEntity> {
        return _movieEntities.value?.filter { it.title?.contains(titleWord, ignoreCase = true) == true } ?: emptyList()
    }
}