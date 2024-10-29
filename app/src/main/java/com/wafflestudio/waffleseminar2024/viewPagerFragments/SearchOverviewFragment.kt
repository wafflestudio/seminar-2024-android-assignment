package com.wafflestudio.waffleseminar2024.viewPagerFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.adapter.GenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchoverviewBinding

interface OnGenreClickListener {
    fun onGenreClick(genreId: Int)
}

class SearchOverviewFragment : Fragment(), OnGenreClickListener {
    private lateinit var navController: NavController

    override fun onGenreClick(genreId: Int) {
        val data: List<Movie> = genreQuery(genreId)
        showResult(data)
    }

    private var _binding: FragmentSearchoverviewBinding? = null
    private val binding get() = _binding!!

    lateinit var genreRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchoverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenreRecyclerView()

        navController = findNavController()

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

        }

        profileButton.setOnClickListener{
            (activity as HomeActivity).viewPager.currentItem = 3
        }
    }

    private fun setGenreRecyclerView(){
        genreRecyclerView = binding.genreRecyclerView
        genreRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        genreRecyclerView.adapter = GenreRecyclerViewAdapter(GenreList, this)
    }


    private fun genreQuery(genreId: Int): List<Movie> {
        return MovieData.filter { movie ->
            movie.genre_ids.contains(genreId)
        }
    }

    private fun showResult(data: List<Movie>) {
        val action = SearchInputFragmentDirections.actionToSearchResultFragment(data.toTypedArray())
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
