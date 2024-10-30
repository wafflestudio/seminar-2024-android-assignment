package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

class SearchResultFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchResultRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.genreRecyclerView.visibility = View.GONE
        Log.d("SearchResultFragment", "genreRecyclerView visibility: ${getVisibilityString(binding.genreRecyclerView.visibility)}")
        binding.searchResultRecyclerView.visibility = View.VISIBLE

        searchResultRecyclerView = binding.searchResultRecyclerView
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

        Log.d("SearchResultFragment", "data size: ${data.size}")

        showResult(data)
    }

    private fun showResult(data: List<Movie>) {
        Log.d("showResult", "1")
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data)
        binding.backButton.visibility = View.VISIBLE

        Log.d("showResult", "searchResultRecyclerView visibility: ${getVisibilityString(searchResultRecyclerView.visibility)}")
        Log.d("showResult", "genreRecyclerView visibility: ${getVisibilityString(binding.genreRecyclerView.visibility)}")

        /*
        searchResultRecyclerView.adapter = MovieAdapter(data) { movie ->
            val bundle = Bundle().apply { putParcelable("movie", movie) }
            findNavController().navigate(R.id.action_searchResult_to_movieDetail, bundle)
        }

         */
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

    private fun getVisibilityString(visibility: Int): String {
        return when (visibility) {
            View.VISIBLE -> "VISIBLE"
            View.INVISIBLE -> "INVISIBLE"
            View.GONE -> "GONE"
            else -> "UNKNOWN"
        }
    }
}
