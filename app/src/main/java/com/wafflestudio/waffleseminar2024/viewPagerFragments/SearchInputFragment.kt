package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import java.net.URLEncoder

class SearchInputFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("SearchInputFragment", "onCreateView")
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SearchInputFragment", "onViewCreated")
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("SearchInputFragment", "editorAction")
                navigateToSearchResult()
                true
            } else {
                false
            }
        }

        binding.searchButton.setOnClickListener {
            Log.d("SearchInputFragment", "searchButton")
            navigateToSearchResult()
        }
    }

    private fun navigateToSearchResult() {
        val searchQuery = binding.searchEditText.text.toString()
        Log.d("navigateToSearchResult", "Original searchQuery: $searchQuery")

        val encodedQuery = URLEncoder.encode(searchQuery, "UTF-8")
        Log.d("navigateToSearchResult", "Encoded searchQuery: $encodedQuery")

        val bundle = Bundle().apply {
            putString("searchQuery", encodedQuery)
        }

        val navController = childFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.findNavController()
        navController?.navigate(R.id.action_searchInput_to_searchResult, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
