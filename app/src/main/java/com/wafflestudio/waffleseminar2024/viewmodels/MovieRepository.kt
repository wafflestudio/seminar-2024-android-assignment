package com.wafflestudio.waffleseminar2024.viewmodels

import com.wafflestudio.waffleseminar2024.database.Movie
import com.wafflestudio.waffleseminar2024.database.MovieDao

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getMoviesByGenre(genreId: Int): List<Movie> {
        return movieDao.getMoviesByGenre(genreId)
    }

    suspend fun getMoviesByQuery(query: String): List<Movie> {
        return movieDao.getMoviesByQuery(query)
    }

    suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

}