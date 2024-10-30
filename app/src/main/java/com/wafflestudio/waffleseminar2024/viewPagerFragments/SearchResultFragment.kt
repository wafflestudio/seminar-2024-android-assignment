package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.MyDatabase
import com.wafflestudio.waffleseminar2024.MyEntity
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

interface OnGenreClickListener {
    fun onGenreClick(genreId: Int)
}

class SearchResultFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    //private lateinit var movieViewModel : MovieViewModel
    private

    fun onGenreClick(genreId: Int) {
        val data: List<Movie> = genreQuery(genreId)
        showResult(data)
    }

    private lateinit var searchResultRecyclerView: RecyclerView
    private var movieEntities: List<MyEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        /*
        val myDao = MyDatabase.getDatabase(requireContext()).myDao()
        movieViewModel = ViewModelProvider(this, MovieViewModelFactory(myDao)).get(MovieViewModel::class.java)

         */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.genreRecyclerView.visibility = View.GONE
        binding.searchResultRecyclerView.visibility = View.VISIBLE

        searchResultRecyclerView = binding.searchResultRecyclerView

        /*
        movieViewModel.fetchAllEntities().observe(viewLifecycleOwner) { entities ->
            movieEntities = entities
            loadData()
        }
         */

        loadData()
    }

    private fun loadData() {
        val genreId = arguments?.getInt("genreId") ?: 0
        val searchQuery = arguments?.getString("searchQuery")
        Log.d("SearchResultFragment", "genreId: $genreId, searchQuery: $searchQuery")

        val data: List<Movie> = if (genreId != 0) {
            Log.d("SearchResultFragment", "Executing genreQuery with genreId: $genreId")
            genreQuery(genreId)
        } else {
            Log.d("SearchResultFragment", "Executing titleQuery with searchQuery: $searchQuery")
            titleQuery(searchQuery ?: "")
        }

        showResult(data)
    }

    private fun showResult(data: List<Movie>) {
        Log.d("showResult", "1")
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data) { movie ->
            navigateToMovieDetail(movie)
        }
        searchResultRecyclerView.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
    }

    private fun navigateToMovieDetail(movie: Movie) {
        Log.d("searchResultFragment", "movie id: ${movie.id}")
        val bundle = Bundle().apply {
            //putInt("movieId", movie.id)
            putInt("movieId", 12)
        }
        findNavController().navigate(R.id.action_searchResult_to_movieDetail, bundle)
        Log.d("searchResultFragment", "navigate success")
    }

    private fun titleQuery(titleWord: String): List<Movie>{
        Log.d("titleQuery", "Filtering for titleWord: $titleWord")
        val filteredMovies = MovieData.filter{ movie ->
            movie.title.contains(titleWord, ignoreCase = true)
        }
        Log.d("titleQuery", "Filtered results count: ${filteredMovies.size}")

        return filteredMovies
    }

    private fun genreQuery(genreId: Int): List<Movie> {
        Log.d("genreQuery", "start")
        return MovieData.filter { movie ->
            movie.genre_ids.contains(genreId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
