package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.GenreList
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieDetailGenreAdapter
import com.wafflestudio.waffleseminar2024.MovieDetailInfoAdapter
import com.wafflestudio.waffleseminar2024.MyDatabase
import com.wafflestudio.waffleseminar2024.MyEntity2
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.MovieDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: MyDatabase
    private var movieDetail: MyEntity2? = null

    private lateinit var movieDetailGenreRecyclerView: RecyclerView
    private lateinit var movieDetailInfoRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailBinding.inflate(inflater, container, false)
        database = MyDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailGenreRecyclerView = binding.genreRecyclerView
        movieDetailInfoRecyclerView = binding.infoRecyclerView

        movieDetailGenreRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        movieDetailInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val movieId = arguments?.getInt("movieId") ?: return
        Log.d("MovieDetailFragment", "movie id: ${movieId}")
        fetchMovieDetail(movieId)

    }

    private fun fetchMovieDetail(movieId: Int) {
        lifecycleScope.launch {
            try {
                movieDetail = withContext(Dispatchers.IO) {
                    database.myDao().getMovieDetailById(movieId)
                }

                if (movieDetail != null) {
                    Log.d("movieDetail", "success")
                    displayMovieDetail()
                } else {
                    Log.e("MovieDetailFragment", "No movie detail found for ID: $movieId")
                }

            } catch (e: Exception) {
                Log.e("MovieDetailFragment", "Error fetching movie detail: ${e.message}", e)
            }
        }
    }

    private fun displayMovieDetail() {
        Log.d("MovieDetailFragment", "title: ${movieDetail?.title}")
        // title
        binding.titleTextView.text = movieDetail?.title

        // image
        val poster_url = "https://image.tmdb.org/t/p/original" + movieDetail?.poster_path
        binding.posterImageView.load(poster_url)

        val backdrop_url = "https://image.tmdb.org/t/p/original" + movieDetail?.backdrop_path
        binding.backdropImageView.load(backdrop_url)

        // rating
        binding.ratingBar.rating = (movieDetail?.vote_average!! / 2).toFloat()
        binding.rating.text = movieDetail?.vote_average.toString()

        // genre
        movieDetailGenreRecyclerView.adapter = movieDetail?.genres?.let { MovieDetailGenreAdapter(it) }

        // info
        val infoList = mutableListOf<Pair<String, String>>().apply {
            add("Summary" to (movieDetail?.overview ?: "N/A"))
            add("Original Title" to (movieDetail?.original_title ?: "N/A"))
            add("Status" to (movieDetail?.status ?: "N/A"))
            add("Budget" to ("$ ${movieDetail?.budget ?: "N/A"}"))
            add("Revenue" to ("$ ${movieDetail?.revenue ?: "N/A"}"))
        }

        movieDetailInfoRecyclerView.adapter = MovieDetailInfoAdapter(infoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
       binding.titleTextView.text = "Finding Nemo"

       // image
       val poster_url = "https://image.tmdb.org/t/p/original" + "/jt1GlLLvkWL2m83VX8I1qsDR187.jpg"
       binding.posterImageView.load(poster_url)

       val backdrop_url = "https://image.tmdb.org/t/p/original" + "/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg"
       binding.backdropImageView.load(backdrop_url)

       // rating
       binding.ratingBar.rating = 7.8F / 2
       binding.rating.text = "7.8"

       // genre
       movieDetailGenreRecyclerView.adapter = MovieDetailGenreAdapter(GenreList)

       // info
       val infoList = mutableListOf<Pair<String, String>>().apply {
           add("Summary" to "따단-딴-따단-딴 ♫ 전 세계를 열광시킬 올 타임 슈퍼 어드벤처의 등장! 뉴욕의 평범한 배관공 형제 '마리오'와 ‘루이지’는 배수관 고장으로 위기에 빠진 도시를 구하려다 미스터리한 초록색 파이프 안으로 빨려 들어가게 된다. 파이프를 통해 새로운 세상으로 차원 이동하게 된 형제. 형 '마리오'는 뛰어난 리더십을 지닌 '피치'가 통치하는 버섯왕국에 도착하지만 동생 '루이지'는 빌런 '쿠파'가 있는 다크랜드로 떨어지며 납치를 당하고 ‘마리오’는 동생을 구하기 위해 ‘피치’와 ‘키노피오’의 도움을 받아 '쿠파'에 맞서기로 결심한다. 그러나 슈퍼스타로 세상을 지배하려는 그의 강력한 힘 앞에 이들은 예기치 못한 위험에 빠지게 되는데...!")
           add("Original Title" to "The Super Mario Bros. Movie")
           add("Status" to "Released")
           add("Budget" to "90000")
           add("Revenue" to "8000")
       }



       movieDetailInfoRecyclerView.adapter = MovieDetailInfoAdapter(infoList)

        */
