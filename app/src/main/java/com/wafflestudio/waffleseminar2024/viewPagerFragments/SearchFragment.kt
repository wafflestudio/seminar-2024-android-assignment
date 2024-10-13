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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.GenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

interface OnGenreClickListener {
    fun onGenreClick(genreId: Int)
}

class SearchFragment : Fragment(), OnGenreClickListener {

    override fun onGenreClick(genreId: Int) {
        val data: List<Movie> = genreQuery(genreId)
        showResult(data)
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    lateinit var searchResultRecyclerView: RecyclerView
    lateinit var genreRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenreRecyclerView()
        setSearchResultRecyclerView()

        val searchEditText: EditText = binding.searchEditText
        val searchButton: ImageView = binding.searchButton
        val profileButton: ImageView = binding.profileButton
        val backButton: ImageView = binding.backButton

        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val data: List<Movie> = titleQuery(searchEditText.text.toString())
                showResult(data)
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }

        searchButton.setOnClickListener{
            val data: List<Movie> = titleQuery(searchEditText.text.toString())
            showResult(data)
        }

        backButton.setOnClickListener{
            hideResult()
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

    private fun setSearchResultRecyclerView(){
        searchResultRecyclerView = binding.searchResultRecyclerView
    }

    private fun titleQuery(titleWord: String): List<Movie>{
        return MovieData.filter{ movie ->
            movie.title.contains(titleWord, ignoreCase = true)
        }
    }

    private fun genreQuery(genreId: Int): List<Movie> {
        return MovieData.filter { movie ->
            movie.genre_ids.contains(genreId)
        }
    }

    private fun showResult(data: List<Movie>) {
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data)
        searchResultRecyclerView.visibility = View.VISIBLE
        genreRecyclerView.visibility = View.INVISIBLE
        binding.backButton.visibility = View.VISIBLE
    }

    private fun hideResult(){
        searchResultRecyclerView.visibility = View.INVISIBLE
        genreRecyclerView.visibility = View.VISIBLE
        binding.backButton.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
