package com.wafflestudio.waffleseminar2024.viewPagerFragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.GenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchresultBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter


class SearchResultFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentSearchresultBinding? = null
    private val binding get() = _binding!!

    lateinit var searchResultRecyclerView: RecyclerView
    lateinit var genreRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchresultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchResultRecyclerView()

        navController = findNavController()

        val args: SearchResultFragmentArgs by navArgs()
        val movies: Array<Movie> = args.movieList

        val searchEditText: EditText = binding.searchEditText
        val searchButton: ImageView = binding.searchButton
        val profileButton: ImageView = binding.profileButton
        val backButton: ImageView = binding.backButton

        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                navController.navigate(R.id.action_to_searchInputFragment)
            }
        }

        backButton.setOnClickListener{
            navController.navigate(R.id.back_to_searchOverviewFragment)
        }

        profileButton.setOnClickListener{
            (activity as HomeActivity).viewPager.currentItem = 3
        }

        showResult(movies.toList())
    }

    private fun setSearchResultRecyclerView(){
        searchResultRecyclerView = binding.searchResultRecyclerView
    }

    private fun showResult(data: List<Movie>) {
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
