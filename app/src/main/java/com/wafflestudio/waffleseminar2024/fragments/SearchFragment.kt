package com.wafflestudio.waffleseminar2024.fragments

import MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.GenreAdapter
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.genreList
import com.wafflestudio.waffleseminar2024.items
//import com.wafflestudio.waffleseminar2024.movieList

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // NavHostFragment를 사용해 탐색 그래프를 초기화합니다.
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.search_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}

//class SearchFragment : Fragment(R.layout.tab_three) {
//    private var isGenreListVisible = true
//    private var movieAdapter: MovieAdapter? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGenres)
//        val searchView = view.findViewById<SearchView>(R.id.searchView)
//        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
//
//        val adapter = GenreAdapter(genreList.genres, items) { genreId ->
//            val filteredMovies = movieList.movies.filter { movie ->
//                genreId in movie.genre_ids
//            }
//            isGenreListVisible = false
//            movieAdapter?.updateMovies(filteredMovies) ?: setMovieAdapter(recyclerView, filteredMovies)
//        }
//
//        toolbar.setNavigationOnClickListener {
//            isGenreListVisible = true
//            setGenreAdapter(recyclerView, adapter)
//        }
//
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        recyclerView.adapter = adapter
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (!query.isNullOrEmpty()) {
//                    val filteredMovies = movieList.movies.filter { movie ->
//                        movie.title.contains(query, ignoreCase = true)
//                    }
//                    isGenreListVisible = false
//                    movieAdapter?.updateMovies(filteredMovies) ?: setMovieAdapter(recyclerView, filteredMovies)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText.isNullOrEmpty()) {
//                    isGenreListVisible = true
//                    setGenreAdapter(recyclerView, adapter)
//                }
//                return true
//            }
//        })
//
//        searchView.setOnCloseListener {
//            isGenreListVisible = true
//            setGenreAdapter(recyclerView, adapter)
//            false
//        }
//    }
//
//    private fun setGenreAdapter(recyclerView: RecyclerView, adapter: GenreAdapter) {
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        recyclerView.adapter = adapter
//        adapter.showGenres()
//    }
//
//    private fun setMovieAdapter(recyclerView: RecyclerView, filteredMovies: List<Movie>) {
//        movieAdapter = MovieAdapter(filteredMovies)
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        recyclerView.adapter = movieAdapter
//    }
//}
