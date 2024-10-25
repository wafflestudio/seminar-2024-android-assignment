package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.viewmodels.SearchViewModel

class SearchInputFragment : Fragment(R.layout.fragment_search_input) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.search_edit_text)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.updateSearchQuery(query)  // ViewModel에 검색어 전달
                    navigateToSearchResult(query)  // 검색 결과 화면으로 이동
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText ?: "")  // 검색어 변경 상태 전달
                return true
            }
        })

        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        viewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            Log.d("SearchInputFragment", "Current query: $query")
        }
    }

    private fun navigateToSearchResult(query: String) {
        val action = SearchInputFragmentDirections
            .actionSearchInputToSearchResult(-1, query)
        findNavController().navigate(action)
    }
}
