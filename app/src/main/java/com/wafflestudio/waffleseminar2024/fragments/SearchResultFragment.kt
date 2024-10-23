package com.wafflestudio.waffleseminar2024.fragments

import MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.database.AppDatabase
//import com.wafflestudio.waffleseminar2024.database.AppDatabase
//import com.wafflestudio.waffleseminar2024.movieList
import kotlinx.coroutines.launch

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

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        // 빈 어댑터로 초기화
        val movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        // Room DB 인스턴스 가져오기
        val db = AppDatabase.getInstance(requireContext())
        val movieDao = db.movieDao()

        lifecycleScope.launch {
            val movies = movieDao.getAllMovies()  // 모든 영화 불러오기

            val filteredMovies = when {
                genreId != -1 -> {
                    // 장르 ID로 필터링
                    movies.filter { movie ->
                        movie.genre_ids?.contains(genreId) == true  // null 안전성 보장
                    }
                }

                !query.isNullOrEmpty() -> {
                    // 검색어로 필터링
                    movies.filter { movie ->
                        val title = movie.title ?: ""  // title이 null일 경우 빈 문자열로 처리
                        val searchQuery = query ?: ""  // query가 null일 경우 빈 문자열로 처리
                        title.contains(searchQuery, ignoreCase = true)
                    }
                }

                else -> {
                    // 둘 다 없을 경우 빈 목록 반환
                    emptyList()
                }
            }

            // 장르 ID 또는 검색어에 따라 영화 목록 필터링
//        val filteredMovies = when {
//            genreId != -1 -> {
//                // 장르 ID로 필터링
//                movieList.movies.filter { genreId in it.genre_ids }
//            }
//            !query.isNullOrEmpty() -> {
//                // 검색어로 필터링
//                movieList.movies.filter { it.title.contains(query, ignoreCase = true) }
//            }
//            else -> {
//                // 둘 다 없을 경우 빈 목록 반환
//                emptyList()
//            }
//        }

            // 필터링된 영화 목록을 RecyclerView에 표시
            recyclerView.adapter = MovieAdapter(filteredMovies)
        }
    }
}
