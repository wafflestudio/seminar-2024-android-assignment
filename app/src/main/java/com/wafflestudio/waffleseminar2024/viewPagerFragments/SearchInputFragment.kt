package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wafflestudio.waffleseminar2024.AppDatabase
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.RecentSearch
import com.wafflestudio.waffleseminar2024.RecentSearchAdapter
import com.wafflestudio.waffleseminar2024.RecentSearchDao
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.SearchInputBinding
import kotlinx.coroutines.launch
import java.net.URLEncoder

class SearchInputFragment : Fragment() {

    private var _binding: SearchInputBinding? = null
    private val binding get() = _binding!!

    private lateinit var recentSearchDao: RecentSearchDao
    private val recentSearchAdapter = RecentSearchAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("SearchInputFragment", "onCreateView")
        _binding = SearchInputBinding.inflate(inflater, container, false)

        val database = AppDatabase.getDatabase(requireContext())
        recentSearchDao = database.recentSearchDao()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchInputRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchInputRecyclerView.adapter = recentSearchAdapter

        recentSearchDao.getRecentSearches().observe(viewLifecycleOwner) { searches ->
            Log.d("SearchInputFragment", "Recent searches: ${searches.size}")
            recentSearchAdapter.submitList(searches)
        }

        Log.d("SearchInputFragment", "onViewCreated")
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("SearchInputFragment", "editorAction")
                saveAndSearch()
                true
            } else {
                false
            }
        }

        binding.searchButton.setOnClickListener {
            Log.d("SearchInputFragment", "searchButton")
            saveAndSearch()
        }
    }

    private fun saveAndSearch() {
        val searchQuery = binding.searchEditText.text.toString().trim()
        if (searchQuery.isNotEmpty()) {
            lifecycleScope.launch {
                Log.d("SearchInputFragment", "Inserting search query: $searchQuery")
                recentSearchDao.insertSearch(RecentSearch(query = searchQuery))
            }
            navigateToSearchResult()
        }
    }

    private fun navigateToSearchResult() {
        val searchQuery = binding.searchEditText.text.toString()
        Log.d("navigateToSearchResult", "Original searchQuery: $searchQuery")

        val encodedQuery = URLEncoder.encode(searchQuery, "UTF-8")
        Log.d("navigateToSearchResult", "Encoded searchQuery: $encodedQuery")

        val bundle = Bundle().apply {
            putString("searchQuery", encodedQuery)
        }
        findNavController().navigate(R.id.action_searchInput_to_searchResult, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
