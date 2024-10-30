package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentMovieDetailBinding
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory

class MovieDetailFragment : Fragment() {
    private lateinit var navController: NavController

    private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(requireContext()) }
    private val movieId: Int by lazy {
        arguments?.getInt("movieId") ?: 0
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        viewModel.fetchMovieDetails(movieId)
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                view.findViewById<TextView>(R.id.movieTitle).text = it.title
            }
        }
    }
}
