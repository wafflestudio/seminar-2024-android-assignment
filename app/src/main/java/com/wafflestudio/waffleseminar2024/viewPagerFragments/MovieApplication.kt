package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.app.Application
import com.wafflestudio.waffleseminar2024.data.MovieDatabase
import com.wafflestudio.waffleseminar2024.data.MovieRepository

class MovieApplication: Application() {
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()
        val movieDao = MovieDatabase.getDatabase(this).MovieDao()
        movieRepository = MovieRepository(movieDao)
    }
}