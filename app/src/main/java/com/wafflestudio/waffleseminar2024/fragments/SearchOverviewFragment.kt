package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.R

class SearchOverviewFragment : Fragment(R.layout.fragment_search_overview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar = view.findViewById<SearchView>(R.id.search_bar)

        searchBar.setOnClickListener {
            // SearchInputFragment로 전환
            findNavController().navigate(R.id.action_searchOverview_to_searchInput)
        }
    }
}
