package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.GenreRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.R


class SearchOverviewFragment : Fragment(), OnGenreClickListener {

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

        /*
        binding.genreRecyclerView.visibility = View.VISIBLE
        binding.searchResultRecyclerView.visibility = View.GONE

         */

        setGenreRecyclerView()

        binding.searchEditText.setOnClickListener {
            /*
            val navController = childFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.findNavController()
            navController?.navigate(R.id.action_searchOverview_to_searchInput)

             */
            findNavController().navigate(R.id.action_searchOverview_to_searchInput)
        }
    }

    private fun setGenreRecyclerView() {
        binding.genreRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.genreRecyclerView.adapter = GenreRecyclerViewAdapter(GenreList, this)
    }

    override fun onGenreClick(genreId: Int) {
        val bundle = Bundle().apply {
            putInt("genreId", genreId)
        }
        findNavController().navigate(R.id.action_searchOverview_to_searchResult, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}