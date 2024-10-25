package com.wafflestudio.waffleseminar2024.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.adapters.GenreAdapter
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.genreList
import com.wafflestudio.waffleseminar2024.items
//import com.wafflestudio.waffleseminar2024.movieList

class SearchOverviewFragment : Fragment(R.layout.tab_three) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGenres)
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        val adapter = GenreAdapter(genreList.genres, items) { genreId ->
//            val filteredMovies = movieList.movies.filter { genreId in it.genre_ids }
//            setMovieAdapter(recyclerView, filteredMovies)
            val action = SearchOverviewFragmentDirections
                .actionSearchOverviewToSearchResult(genreId, null)
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Log.d("fragment", "SearchView focused")
                findNavController().navigate(R.id.action_searchOverview_to_searchInput)
            }
        }


    }


}
