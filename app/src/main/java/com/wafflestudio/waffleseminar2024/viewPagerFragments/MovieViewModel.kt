package com.wafflestudio.waffleseminar2024.viewPagerFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.wafflestudio.waffleseminar2024.data.MovieEntity
import com.wafflestudio.waffleseminar2024.data.MovieRepository
import kotlinx.coroutines.launch

data class Genre(
    val id: Int,
    val name: String
)

class MovieViewModel (private val repository: MovieRepository): ViewModel() {

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return MovieViewModel(
                    (application as MovieApplication).movieRepository,
                ) as T
            }
        }
    }
    private val _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>> = _movieList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private fun loadAllMovies(){
//        _movieList.value = repository.getAllMovies()
    }

    private fun loadMovieById(id: Int){
//        _movieList.value = listOf(repository.getMovieById(id))
    }
    fun loadMovieByGenre(genre: Int){
        viewModelScope.launch {
            val movies = repository.getMoviesByGenreId(genre)
            _movieList.value = movies
        }
    }
    fun loadMovieByTitleQuery(titleQuery: String){
        viewModelScope.launch {
            val movies = repository.getMovieByTitleQuery(titleQuery)
            _movieList.value = movies
        }
    }
}