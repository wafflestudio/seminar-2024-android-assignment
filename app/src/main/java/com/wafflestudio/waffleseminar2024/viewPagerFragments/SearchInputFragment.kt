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
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.Movie
//import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchinputBinding
import com.wafflestudio.waffleseminar2024.adapter.searchTermRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory
import com.wafflestudio.waffleseminar2024.viewmodel.SearchInputViewmodel

class SearchInputFragment: Fragment() {
    private lateinit var navController: NavController

    private var _binding: FragmentSearchinputBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchTermRecyclerView: RecyclerView

    private val searchInputViewModel: SearchInputViewmodel by activityViewModels()
    private val movieViewModel: MovieViewModel by viewModels { MovieViewModelFactory(requireContext()) }

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
                searchInputViewModel.saveSearchTerm(searchTerm)
                movieViewModel.titleQuery(searchTerm)  // 검색 쿼리 실행
                hideKeyboard(v)
                true
            } else {
                false
            }
        }

        searchEditText.requestFocus()
        showKeyboard(searchEditText)

        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString()
            searchInputViewModel.saveSearchTerm(searchTerm)
            movieViewModel.titleQuery(searchTerm)
        }

        backButton.setOnClickListener {
            navController.navigate(R.id.back_to_searchOverviewFragment)
        }

        // 검색 결과 LiveData 관찰
        movieViewModel.searchResults.observe(viewLifecycleOwner) { data ->
            showResult(data)
        }
    }

    private fun setSearchTermRecyclerView() {
        searchTermRecyclerView = binding.searchTermRecyclerView
        searchTermRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchInputViewModel.searchTerms.observe(viewLifecycleOwner) { recentSearchTerms ->
            searchTermRecyclerView.adapter = searchTermRecyclerViewAdapter(recentSearchTerms)
            recentSearchTerms.forEach { term ->
                Log.d("SearchInputFragment", "Recent Search Term: $term")
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showKeyboard(editText: EditText) {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
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