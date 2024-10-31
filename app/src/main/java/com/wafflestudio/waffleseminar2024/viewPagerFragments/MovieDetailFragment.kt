package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.MovieDetailGenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentMovieDetailBinding
import java.text.NumberFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MovieDetailFragment: Fragment()  {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).movieRepository)
    }

    lateinit var  movieDetailGenreRecyclerView: RecyclerView
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backDropImage: ImageView = binding.backdropImage
        val posterImage: ImageView = binding.posterImage
        val titleText: TextView = binding.titleText
        val ratingText: TextView = binding.ratingText
        val rating: RatingBar = binding.rating
        val runTimeText: TextView = binding.runtimeText
        val releasedDate: TextView = binding.publishedYearText
        val summaryText: TextView = binding.summaryText
        val originalTitleText: TextView = binding.originalTitleText
        val statusText: TextView = binding.statusText
        val budgetText: TextView = binding.budgetText
        val revenueText: TextView = binding.revenueText
        val backButton: ImageView = binding.backButton
        movieDetailGenreRecyclerView = binding.genreRecyclerView
        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer{ items ->
            posterImage.load("https://image.tmdb.org/t/p/original" + items?.poster_path)
            backDropImage.load("https://image.tmdb.org/t/p/original" + items?.backdrop_path)
            titleText.text = items?.title
            ratingText.text = ((items?.vote_average!!.times(10).roundToInt().div(10.0))).toString()
            rating.numStars = 5
            rating.rating = items?.vote_average!! / 2
            val minutes = items.runtime!!
            runTimeText.text = "${minutes/60}h ${minutes%60}m"
            releasedDate.text = items.release_date!!.split("-")[0]
            summaryText.text = items.overview
            originalTitleText.text = items.original_title
            statusText.text = items.status
            budgetText.text = "$" + NumberFormat.getInstance().format(items.budget)
            revenueText.text = "$" + NumberFormat.getInstance().format(items.revenue)
            movieDetailGenreRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            movieDetailGenreRecyclerView.adapter = MovieDetailGenreRecyclerViewAdapter(items.genres)
            backButton.setOnClickListener{
                findNavController().navigate(R.id.detail_to_result)
            }
        })

//        movieViewModel.movieDetail.observe
//        val backButton: ImageView = binding.backButton
//
//        backButton.setOnClickListener{
//            findNavController().navigate(R.id.result_to_overview)
//        }

    }


}