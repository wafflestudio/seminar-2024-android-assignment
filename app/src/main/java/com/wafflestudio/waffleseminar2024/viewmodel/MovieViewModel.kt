package com.wafflestudio.waffleseminar2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<MyEntity>()
    val movie: LiveData<MyEntity> get() = _movie

    private val _searchResults = MutableLiveData<List<Movie>>()
    val searchResults: LiveData<List<Movie>> get() = _searchResults

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            // IO 스레드에서 데이터베이스 작업 수행
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)  // 데이터베이스에서 영화 정보 가져오기
            }
            _movie.value = movieDetails
        }
    }

    fun titleQuery(titleWord: String) {
        viewModelScope.launch {
            // IO 스레드에서 데이터베이스 작업 수행
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByTitle(titleWord).map { entity ->
                    Movie(
                        id = entity.id ?: 0,
                        backdrop_path = entity.backdrop_path ?: "",
                        genre_ids = entity.genre_ids ?: emptyList(),
                        original_language = entity.original_language ?: "",
                        original_title = entity.original_title ?: "",
                        overview = entity.overview ?: "",
                        popularity = entity.popularity ?: 0f,
                        poster_path = entity.poster_path ?: "",
                        release_date = entity.release_date ?: "",
                        title = entity.title ?: "",
                        vote_average = entity.vote_average ?: 0f,
                        vote_count = entity.vote_count ?: 0
                    )
                }
            }
            _searchResults.value = movies  // 검색 결과 업데이트
        }
    }

    fun genreQuery(genreId: Int) {
        viewModelScope.launch {
            // IO 스레드에서 데이터베이스 작업 수행
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByGenre(genreId).map { entity ->
                    Movie(
                        id = entity.id ?: 0,
                        backdrop_path = entity.backdrop_path ?: "",
                        genre_ids = entity.genre_ids ?: emptyList(),
                        original_language = entity.original_language ?: "",
                        original_title = entity.original_title ?: "",
                        overview = entity.overview ?: "",
                        popularity = entity.popularity ?: 0f,
                        poster_path = entity.poster_path ?: "",
                        release_date = entity.release_date ?: "",
                        title = entity.title ?: "",
                        vote_average = entity.vote_average ?: 0f,
                        vote_count = entity.vote_count ?: 0
                    )
                }
            }
            _searchResults.value = movies  // 검색 결과 업데이트
        }
    }
}