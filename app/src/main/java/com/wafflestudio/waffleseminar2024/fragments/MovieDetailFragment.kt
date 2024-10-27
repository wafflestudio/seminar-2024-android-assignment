package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
import java.text.NumberFormat
import java.util.Locale

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
        val backdropImageView = view.findViewById<ImageView>(R.id.backdropImageView)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val ratingTextView = view.findViewById<TextView>(R.id.ratingTextView)

        val genresContainer = view.findViewById<LinearLayout>(R.id.genresContainer)
        val budgetTextView = view.findViewById<TextView>(R.id.budgetTextView)
        val revenueTextView = view.findViewById<TextView>(R.id.revenueTextView)
        val originalTitleView = view.findViewById<TextView>(R.id.originalTitleView)
        val statusView = view.findViewById<TextView>(R.id.statusView)
        posterImageView.load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
        backdropImageView.load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")


        // 제목, 줄거리, 별점 설정
        titleTextView.text = movie.title ?: "No Title"
        overviewTextView.text = movie.overview ?: "No Overview"
        // 별점 설정: vote_average가 null일 경우 기본값을 0.0으로 설정
        val rating = (movie.vote_average ?: 0.0).toFloat()
        ratingBar.rating = rating
        ratingTextView.text = String.format("%.1f", rating)  // 소수점 한 자리까지 표시



        genresContainer.removeAllViews()
        val genreNames = movie.genres?.split("Genre(")?.mapNotNull { extractGenreName(it) } ?: emptyList()

        genreNames.forEach { genreName ->
            val genreView = TextView(requireContext()).apply {
                text = genreName  // 추출한 name만 사용
                setPadding(16, 8, 16, 8)  // 내부 여백 설정
                setBackgroundResource(R.drawable.genre_background)  // 배경 적용
                setTextColor(ContextCompat.getColor(context, android.R.color.black))  // 텍스트 색상
            }

            // 마진 설정
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 16, 8, 16)  // 왼쪽, 위, 오른쪽, 아래 마진 설정
            }

            genreView.layoutParams = params
            genresContainer.addView(genreView)
        }



        // Budget과 Revenue 통화 형식 설정
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        budgetTextView.text = format.format(movie.budget)
        revenueTextView.text = format.format(movie.revenue)
        originalTitleView.text = movie.original_title ?: "No original title"
        statusView.text = movie.status ?: "No status"
    }
}

// name= 뒤의 장르 이름만 추출하는 함수
private fun extractGenreName(genreString: String): String? {
    val regex = Regex("name=([^)]*)")  // 정규 표현식으로 name= 이후 부분 추출
    val matchResult = regex.find(genreString)
    return matchResult?.groupValues?.get(1)?.trim()  // 일치하는 그룹의 값을 반환
}