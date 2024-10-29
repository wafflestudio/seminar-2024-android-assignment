package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchinputBinding
import com.wafflestudio.waffleseminar2024.adapter.searchTermRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.viewmodel.SearchInputViewmodel

class SearchInputFragment: Fragment() {
    private lateinit var navController: NavController

    private var _binding: FragmentSearchinputBinding? = null
    private val binding get() = _binding!!

    lateinit var searchTermRecyclerView: RecyclerView

    private val viewModel: SearchInputViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchinputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        setSearchTermRecyclerView()

        val searchEditText: EditText = binding.searchEditText
        val searchButton: ImageView = binding.searchButton
        val backButton: ImageView = binding.backButton


        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchTerm = searchEditText.text.toString()
                viewModel.saveSearchTerm(searchTerm)
                val data: List<Movie> = titleQuery(searchTerm)
                showResult(data)
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }

        searchEditText.requestFocus()
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)


        searchButton.setOnClickListener{
            val searchTerm = searchEditText.text.toString()
            viewModel.saveSearchTerm(searchTerm)
            val data: List<Movie> = titleQuery(searchEditText.text.toString())
            showResult(data)
        }

        backButton.setOnClickListener{
            navController.navigate(R.id.back_to_searchOverviewFragment)
        }
    }

    private fun setSearchTermRecyclerView() {
        searchTermRecyclerView = binding.searchTermRecyclerView
        searchTermRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.searchTerms.observe(viewLifecycleOwner) { recentSearchTerms ->
            searchTermRecyclerView.adapter = searchTermRecyclerViewAdapter(recentSearchTerms)
            for (term in recentSearchTerms) {
                Log.d("SearchInputFragment", "Recent Search Term: $term")
            }
        }
    }


    private fun titleQuery(titleWord: String): List<Movie>{
        return MovieData.filter{ movie ->
            movie.title.contains(titleWord, ignoreCase = true)
        }
    }

    private fun showResult(data: List<Movie>) {
        val action = SearchInputFragmentDirections.actionToSearchResultFragment(data.toTypedArray())
        navController.navigate(action)
    }
}