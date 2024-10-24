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
import com.wafflestudio.waffleseminar2024.database.MovieDetail
import com.wafflestudio.waffleseminar2024.database.MovieDetailDatabase
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val movieId = arguments?.getInt("movieId") ?: -1
        Log.d("MovieDetailFragment", "Received movieId: $movieId")  // 전달된 ID 확인

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        // Toolbar 설정
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)  // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            // SearchOverviewFragment로 돌아가기
            findNavController().popBackStack(R.id.searchOverviewFragment, false)
        }


        // ID를 TextView에 표시
//        if (movieId != -1) {
//
//            movieIdTextView.text = "Movie ID: $movieId"
//        } else {
//            movieIdTextView.text = "Invalid Movie ID"
//        }

//        if (movieId != -1) {
//            // Room DB 인스턴스 가져오기
//            val db = MovieDetailDatabase.getInstance(requireContext())
//            val movieDetailDao = db.movieDetailDao()
//
//            // 코루틴을 사용해 DB에서 데이터 가져오기
//            lifecycleScope.launch {
//                val movieDetail = movieDetailDao.getMovieDetailById(movieId)
//                if (movieDetail != null) {
//                    // 영화 제목을 TextView에 표시
//                    movieIdTextView.text = movieDetail.title ?: "No Title Found"
//                } else {
//                    movieIdTextView.text = "Movie not found"
//                }
//            }
//        } else {
//            movieIdTextView.text = "Invalid Movie ID"
//        }

        if (movieId != -1) {

            val db = MovieDetailDatabase.getInstance(requireContext())
            val movieDetailDao = db.movieDetailDao()

            lifecycleScope.launch {
                val movieDetail = movieDetailDao.getMovieDetailById(movieId)
                movieDetail?.let { displayMovieDetails(it, view) }
            }
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
