package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchInputBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchOverviewBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchResultBinding
import com.wafflestudio.waffleseminar2024.searchResultRecyclerViewAdapter

class SearchResultFragment: Fragment()  {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).movieRepository)
    }
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchResultRecyclerView: RecyclerView = binding.searchResultRecyclerView
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        movieViewModel.movieList.observe(viewLifecycleOwner, Observer{ items ->
            searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(items)
        })

        val searchLinearLayout: LinearLayout = binding.searchLinearLayout
        val searchEditText: EditText = binding.searchEditText
        val backButton: ImageView = binding.backButton

        searchLinearLayout.setOnClickListener{
            findNavController().navigate(R.id.result_to_input)
        }
        searchEditText.setOnFocusChangeListener(object: OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus){
                    findNavController().navigate(R.id.result_to_input)
                }
            }
        })
        backButton.setOnClickListener{
            findNavController().navigate(R.id.result_to_overview)
        }

    }
}