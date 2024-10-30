package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MyDatabase
import com.wafflestudio.waffleseminar2024.MyEntity2
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.MovieDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: MyDatabase
    private lateinit var movieDetail: MyEntity2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailBinding.inflate(inflater, container, false)
        database = MyDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt("movieId") ?: return
        Log.d("MovieDetailFragment", "movie id: ${movieId}")
        fetchMovieDetail(movieId)

    }

    private fun fetchMovieDetail(movieId: Int) {
        lifecycleScope.launch {
            try {
                movieDetail = withContext(Dispatchers.IO) {
                    database.myDao().getMovieDetailById(movieId)
                }
                displayMovieDetail()
            } catch (e: Exception) {
                Log.e("MovieDetailFragment", "Error fetching movie detail: ${e.message}", e)
            }
        }
    }

    private fun displayMovieDetail() {
        Log.d("MovieDetailFragment", "title: ${movieDetail.title}")
        binding.titleTextView.text = movieDetail.title
        //.genreTextView.text = movieDetail.genres?.joinToString { it.name } // 장르 표시
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
