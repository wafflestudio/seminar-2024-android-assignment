package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.EntityViewModel
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.MyDatabase
import com.wafflestudio.waffleseminar2024.MyEntity
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.SearchResultBinding
import com.wafflestudio.waffleseminar2024.databinding.SearchResultRecyclerviewItemBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

interface OnGenreClickListener {
    fun onGenreClick(genreId: Int)
}

class SearchResultFragment : Fragment() {

    private var _binding: SearchResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var entityViewModel: EntityViewModel
    private lateinit var searchResultRecyclerView: RecyclerView
    private var movieEntities: List<MyEntity> = emptyList()


    fun onGenreClick(genreId: Int) {
        val data: List<MyEntity> = entityViewModel.genreQuery(genreId)
        showResult(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchResultBinding.inflate(inflater, container, false)
        entityViewModel = ViewModelProvider(this).get(EntityViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultRecyclerView = binding.searchResultRecyclerView

        entityViewModel.movieEntities.observe(viewLifecycleOwner) { entities ->
            if (!entities.isNullOrEmpty()) {
                loadData()
            }
        }

    }

    private fun loadData() {
        val genreId = arguments?.getInt("genreId") ?: 0
        val searchQuery = arguments?.getString("searchQuery")

        val data: List<MyEntity> = if (genreId != 0) {
            entityViewModel.genreQuery(genreId)
        } else {
            entityViewModel.titleQuery(searchQuery ?: "")
        }

        showResult(data)
    }

    private fun showResult(data: List<MyEntity>) {
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data) { entity ->
            navigateToMovieDetail(entity)
        }
        searchResultRecyclerView.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
    }

    private fun navigateToMovieDetail(entity: MyEntity) {
        entity.id?.let { id ->
            val bundle = Bundle().apply {
                putInt("movieId", id)
            }
            findNavController().navigate(R.id.action_searchResult_to_movieDetail, bundle)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
