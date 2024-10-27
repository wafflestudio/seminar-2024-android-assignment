package com.wafflestudio.waffleseminar2024.viewPagerFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.data.MovieEntity
import com.wafflestudio.waffleseminar2024.data.MovieRepository

data class Genre(
    val id: Int,
    val name: String
)

class MovieViewModel (private val repository: MovieRepository): ViewModel() {

    private val _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>> = _movieList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private fun loadAllMovies(){
        _movieList.value = repository.getAllMovies()
    }

    private fun loadMovieById(id: Int){
        _movieList.value = listOf(repository.getMovieById(id))
    }


}