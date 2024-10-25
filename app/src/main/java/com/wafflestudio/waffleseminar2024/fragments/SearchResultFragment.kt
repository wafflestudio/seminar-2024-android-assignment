package com.wafflestudio.waffleseminar2024.fragments


import com.wafflestudio.waffleseminar2024.adapters.MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.viewmodels.MovieRepository
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.database.AppDatabase
import com.wafflestudio.waffleseminar2024.viewmodels.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodels.MovieViewModelFactory

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {
    private val args: SearchResultFragmentArgs by navArgs()  // Safe Args로 검색어 받기

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(AppDatabase.getInstance(requireContext()).movieDao()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        // Toolbar 설정
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)  // 뒤로가기 아이콘 설정
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.searchOverviewFragment, false)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGenres)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        val movieAdapter = MovieAdapter(emptyList()) { movieId ->
            val action = SearchResultFragmentDirections.actionSearchResultFragmentToMovieDetailFragment(movieId)
            findNavController().navigate(action)
        }
        recyclerView.adapter = movieAdapter

        // ViewModel의 영화 목록을 관찰하여 RecyclerView 업데이트
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies)
        }

        // ViewModel을 통해 영화 목록 로드
        val genreId = args.genreId
        val query = args.query
        viewModel.loadMovies(genreId, query)
    }
}
