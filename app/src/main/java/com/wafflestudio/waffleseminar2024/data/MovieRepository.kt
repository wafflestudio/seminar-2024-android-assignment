package com.wafflestudio.waffleseminar2024.data

class MovieRepository(private val movieDao: MovieDao) {

    fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    fun getAllMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    fun getMovieById(id: Int): MovieEntity {
        return movieDao.getMovieById(id)
    }

}
