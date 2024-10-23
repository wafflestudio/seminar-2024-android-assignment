package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieData
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchinputBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchoverviewBinding

class SearchInputFragment: Fragment() {
    private lateinit var navController: NavController

    private var _binding: FragmentSearchinputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchinputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val searchEditText: EditText = binding.searchEditText
        val searchButton: ImageView = binding.searchButton
        val profileButton: ImageView = binding.profileButton
        val backButton: ImageView = binding.backButton

        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val data: List<Movie> = titleQuery(searchEditText.text.toString())
                showResult(data)
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }


        searchButton.setOnClickListener{
            val data: List<Movie> = titleQuery(searchEditText.text.toString())
            showResult(data)
        }

        backButton.setOnClickListener{

        }

        profileButton.setOnClickListener{
            (activity as HomeActivity).viewPager.currentItem = 3
        }
    }

    private fun titleQuery(titleWord: String): List<Movie>{
        return MovieData.filter{ movie ->
            movie.title.contains(titleWord, ignoreCase = true)
        }
    }

    private fun showResult(data: List<Movie>) {

    }
}