package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.adapters.SearchHistoryAdapter
import com.wafflestudio.waffleseminar2024.database.MovieSearchDatabase
import com.wafflestudio.waffleseminar2024.database.SearchQuery
import com.wafflestudio.waffleseminar2024.viewmodels.SearchViewModel
import com.wafflestudio.waffleseminar2024.viewmodels.SearchViewModelFactory
import kotlinx.coroutines.launch

class SearchInputFragment : Fragment(R.layout.fragment_search_input) {

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(MovieSearchDatabase.getInstance(requireContext()))
    }

    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.search_edit_text)
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchHistoryRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // viewModel.loadSearchHistory()
        // 검색 기록을 RecyclerView에 표시
        searchHistoryAdapter = SearchHistoryAdapter(
            onDeleteClick = { query -> deleteSearchQuery(query) },
            onItemClick = { query -> navigateToSearchResult(query) }
        )
        recyclerView.adapter = searchHistoryAdapter

        // 검색 기록을 ViewModel에서 불러와 갱신
        viewModel.searchHistory.observe(viewLifecycleOwner) { history ->
            searchHistoryAdapter.updateQueries(history)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.updateSearchQuery(query)  // 검색어 저장
                    navigateToSearchResult(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // viewModel.updateSearchQuery(newText ?: "")  // 검색어 변경 추적
                return true
            }
        })

        viewModel.loadSearchHistory()  // 검색 기록 로드
    }

    private fun navigateToSearchResult(query: String) {
        val action = SearchInputFragmentDirections
            .actionSearchInputToSearchResult(-1, query)
        findNavController().navigate(action)
    }

    private fun deleteSearchQuery(query: SearchQuery) {
        lifecycleScope.launch {
            viewModel.deleteSearchQuery(query)
        }
    }
}
