package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.database.AppDatabase
import com.wafflestudio.waffleseminar2024.database.MovieDetailDatabase
import com.wafflestudio.waffleseminar2024.database.MovieDetail
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val args: MovieDetailFragmentArgs by navArgs()  // Safe Args로 전달된 movieId 받기

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        setupToolbar(toolbar)

        val movieId = args.movieId  // 전달받은 영화 ID
        Log.d("MovieDetailFragment", "Received movieId: $movieId")

        if (movieId != -1) {
            // Room DB에서 데이터 가져오기
            val db = MovieDetailDatabase.getInstance(requireContext())
            val movieDetailDao = db.movieDetailDao()

            lifecycleScope.launch {
                val movieDetail = movieDetailDao.getMovieDetailById(movieId)
                movieDetail?.let { displayMovieDetails(it, view) }
            }
        }
    }

    private fun setupToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun displayMovieDetails(movie: MovieDetail, view: View) {
        val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)
        val releaseDateTextView = view.findViewById<TextView>(R.id.releaseDateTextView)

        posterImageView.load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
        titleTextView.text = movie.title ?: "No Title"
        overviewTextView.text = movie.overview ?: "No Overview"
        releaseDateTextView.text = "Release Date: ${movie.release_date ?: "Unknown"}"
    }
}
