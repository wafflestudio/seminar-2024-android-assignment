package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieDetailGenreAdapter
import com.wafflestudio.waffleseminar2024.MovieDetailInfoAdapter
import com.wafflestudio.waffleseminar2024.MyDatabase
import com.wafflestudio.waffleseminar2024.MyEntity2
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.MovieDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: MyDatabase
    private var movieDetail: MyEntity2? = null

    private lateinit var movieDetailGenreRecyclerView: RecyclerView
    private lateinit var movieDetailInfoRecyclerView: RecyclerView

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

        movieDetailGenreRecyclerView = binding.genreRecyclerView
        movieDetailInfoRecyclerView = binding.infoRecyclerView

        movieDetailGenreRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        movieDetailInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

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

                if (movieDetail != null) {
                    Log.d("movieDetail", "success")
                    displayMovieDetail()
                } else {
                    Log.e("MovieDetailFragment", "No movie detail found for ID: $movieId")
                }

            } catch (e: Exception) {
                Log.e("MovieDetailFragment", "Error fetching movie detail: ${e.message}", e)
            }
        }
    }

    private fun displayMovieDetail() {
        Log.d("MovieDetailFragment", "title: ${movieDetail?.title}")
        // title
        binding.titleTextView.text = movieDetail?.title

        // image
        val poster_url = "https://image.tmdb.org/t/p/original" + movieDetail?.poster_path
        binding.posterImageView.load(poster_url)

        val backdrop_url = "https://image.tmdb.org/t/p/original" + movieDetail?.backdrop_path
        binding.backdropImageView.load(backdrop_url)

        // rating
        binding.ratingBar.rating = (movieDetail?.vote_average!! / 2).toFloat()
        binding.rating.text = String.format("%.1f", movieDetail?.vote_average)

        // genre
        movieDetailGenreRecyclerView.adapter = movieDetail?.genres?.let { MovieDetailGenreAdapter(it) }

        // info
        val infoList = mutableListOf<Pair<String, String>>().apply {
            add("Summary" to (movieDetail?.overview ?: "N/A"))
            add("Original Title" to (movieDetail?.original_title ?: "N/A"))
            add("Status" to (movieDetail?.status ?: "N/A"))

            val formattedBudget = movieDetail?.budget?.let { "$ ${NumberFormat.getNumberInstance(Locale.US).format(it)}" } ?: "N/A"
            add("Budget" to formattedBudget)
            val formattedRevenue = movieDetail?.revenue?.let { "$ ${NumberFormat.getNumberInstance(Locale.US).format(it)}" } ?: "N/A"
            add("Revenue" to formattedRevenue)
        }

        movieDetailInfoRecyclerView.adapter = MovieDetailInfoAdapter(infoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}