package com.wafflestudio.waffleseminar2024.data.database


class MovieRepository(private val myDao: MyDao) {

    fun getMovieById(id: Int): MyEntity {
        return myDao.getMyEntityById(id)
    }

    fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        return myDao.getMoviesByTitle(titleWord)
    }

    fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        return myDao.getMoviesByGenre(genreId)
    }
}
