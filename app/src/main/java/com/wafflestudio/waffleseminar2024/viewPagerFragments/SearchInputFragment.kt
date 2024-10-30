package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.data.MovieDao
import com.wafflestudio.waffleseminar2024.data.MovieDatabase
import com.wafflestudio.waffleseminar2024.data.MovieRepository
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchInputBinding

class SearchInputFragment: Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).movieRepository)
    }
    private var _binding: FragmentSearchInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchInputBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: ImageView = binding.backButton
        val editText: EditText = binding.searchEditText



        backButton.setOnClickListener{
            findNavController().navigate(R.id.input_to_overview)
        }

        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                movieViewModel.loadMovieByTitleQuery(editText.text.toString())
                findNavController().navigate(R.id.input_to_result)
                true
            } else {
                false
            }
        }
    }


}