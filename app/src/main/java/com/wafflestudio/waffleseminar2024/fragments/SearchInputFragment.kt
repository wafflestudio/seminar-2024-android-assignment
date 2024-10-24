package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.R
//import com.wafflestudio.waffleseminar2024.movieList


class SearchInputFragment : Fragment(R.layout.fragment_search_input) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.search_edit_text)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // 검색어를 SearchResultFragment로 전달
                    val action = SearchInputFragmentDirections
                        .actionSearchInputToSearchResult(-1, query)
                    findNavController().navigate(action)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}

//class SearchInputFragment : Fragment(R.layout.fragment_search_input) {
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val searchEditText = view.findViewById<EditText>(R.id.search_edit_text)
//        val recentSearchesList = view.findViewById<ListView>(R.id.recent_searches_list)
//        Log.d("fragment", "second")
//
//        searchEditText.setOnEditorActionListener { _, _, _ ->
//            val query = searchEditText.text.toString()
//            if (query.isNotEmpty()) {
//                // 검색 동작 수행 (예: 서버에서 검색 결과 가져오기)
//                // 최근 검색어 목록에 추가
//            }
//            true
//        }
//    }
//}