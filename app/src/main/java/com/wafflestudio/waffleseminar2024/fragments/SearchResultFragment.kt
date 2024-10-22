package com.wafflestudio.waffleseminar2024.fragments

import MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.movieList

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {
    private val args: SearchResultFragmentArgs by navArgs()  // Safe Args로 검색어 받기

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        // Toolbar 설정
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)  // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            // SearchOverviewFragment로 돌아가기
            findNavController().popBackStack(R.id.searchOverviewFragment, false)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGenres)

        val genreId = args.genreId
        val query = args.query
        // 장르 ID 또는 검색어에 따라 영화 목록 필터링
        val filteredMovies = when {
            genreId != -1 -> {
                // 장르 ID로 필터링
                movieList.movies.filter { genreId in it.genre_ids }
            }
            !query.isNullOrEmpty() -> {
                // 검색어로 필터링
                movieList.movies.filter { it.title.contains(query, ignoreCase = true) }
            }
            else -> {
                // 둘 다 없을 경우 빈 목록 반환
                emptyList()
            }
        }

        // 필터링된 영화 목록을 RecyclerView에 표시
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = MovieAdapter(filteredMovies)
    }
}
