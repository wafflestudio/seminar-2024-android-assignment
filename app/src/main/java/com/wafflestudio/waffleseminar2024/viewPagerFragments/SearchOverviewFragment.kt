package com.wafflestudio.waffleseminar2024.viewPagerFragments


import GenreList
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.GenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.data.MovieEntity
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchOverviewBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

interface OnGenreClickListener {
    fun onGenreClick(genreId: Int)
}

class SearchOverviewFragment : Fragment(), OnGenreClickListener {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).movieRepository)
    }

    override fun onGenreClick(genreId: Int) {
        movieViewModel.loadMovieByGenre(genreId)
        findNavController().navigate(R.id.overview_to_result)
    }

    private var _binding: FragmentSearchOverviewBinding? = null
    private val binding get() = _binding!!

    lateinit var genreRecyclerView: RecyclerView

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenreRecyclerView()

        val searchEditText: EditText = binding.searchEditText
        val searchButton: ImageView = binding.searchButton
        val profileButton: ImageView = binding.profileButton
        val backButton: ImageView = binding.backButton
        val searchLinearLayout: LinearLayout = binding.searchLinearLayout

        navController = findNavController()
        searchLinearLayout.setOnClickListener{
            navController.navigate(R.id.clickSearchBar)
        }
        searchEditText.setOnFocusChangeListener(object: OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus){
                    navController.navigate(R.id.clickSearchBar)
                }
            }
        })


        profileButton.setOnClickListener{
            (activity as HomeActivity).viewPager.currentItem = 3
        }
    }

    private fun setGenreRecyclerView(){
        genreRecyclerView = binding.genreRecyclerView
        genreRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        genreRecyclerView.adapter = GenreRecyclerViewAdapter(GenreList, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
