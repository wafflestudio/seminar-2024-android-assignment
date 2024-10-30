package com.wafflestudio.waffleseminar2024.data

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun getAllMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun getMovieById(id: Int): MovieEntity {
        return movieDao.getMovieById(id)
    }

    suspend fun getMovieByTitleQuery(titleQuery: String): List<MovieEntity>{
        return movieDao.getMovieByTitleQuery(titleQuery)
    }

    suspend fun getMoviesByGenreId(id: Int): List<MovieEntity>{
        return movieDao.getMoviesByGenreId(id)
    }

}
