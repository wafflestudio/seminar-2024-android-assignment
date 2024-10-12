package com.wafflestudio.waffleseminar2024


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var searchResultRecyclerView: RecyclerView
    lateinit var genreRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchEditText: EditText = binding.searchEditText
        val searchButton: Button = binding.searchButton

        searchButton.text = "검색"

        genreRecyclerView = binding.genreRecyclerView
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        genreRecyclerView.layoutManager = gridLayoutManager
        genreRecyclerView.adapter = GenreRecyclerViewAdapter(GenreList)

        searchResultRecyclerView = binding.searchResultRecyclerView
        val data: List<Movie> = titleQuery("e")

        searchButton.setOnClickListener{
            val data: List<Movie> = titleQuery(searchEditText.text.toString())
            showResult(data)
        }
    }

    private fun showResult(data: List<Movie>) {
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data)
        searchResultRecyclerView.visibility = View.VISIBLE
        genreRecyclerView.visibility = View.INVISIBLE
    }


    private fun titleQuery(query: String): List<Movie>{
        return MovieData.filter{ movie ->
            movie.title.contains(query, ignoreCase = true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
