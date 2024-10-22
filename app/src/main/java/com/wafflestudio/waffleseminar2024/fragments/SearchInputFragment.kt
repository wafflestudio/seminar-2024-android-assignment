package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.wafflestudio.waffleseminar2024.R

class SearchInputFragment : Fragment(R.layout.fragment_search_input) {

    private lateinit var searchEditText: EditText
    private lateinit var recentSearchesListView: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText = view.findViewById(R.id.search_edit_text)
        recentSearchesListView = view.findViewById(R.id.recent_searches_list)

        // 최근 검색어 표시 및 검색 이벤트 처리
        loadRecentSearches()
    }

    private fun loadRecentSearches() {
        // SharedPreferences에서 검색 기록을 불러와 리스트에 표시합니다.
    }
}
