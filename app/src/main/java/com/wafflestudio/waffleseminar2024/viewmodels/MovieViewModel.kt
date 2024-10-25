package com.wafflestudio.waffleseminar2024.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.database.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun loadMovies(genreId: Int = -1, query: String? = null){
        viewModelScope.launch{
            val result = when{
                genreId != -1 -> repository.getMoviesByGenre(genreId)
                !query.isNullOrEmpty() -> repository.getMoviesByQuery(query)
                else -> repository.getAllMovies()
            }
            _movies.value = result
        }
    }

}