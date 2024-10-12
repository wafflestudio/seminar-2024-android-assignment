package com.wafflestudio.waffleseminar2024


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

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

        val recyclerView = binding.genreRecyclerView
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = GenreRecyclerViewAdapter(GenreList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
