package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.databinding.ItemMoviePosterBinding

class MoviePosterAdapter : RecyclerView.Adapter<MoviePosterAdapter.MovieViewHolder>() {

    private var movieList: List<SearchViewModel.Movie> = emptyList()

    // ViewHolder 정의
    class MovieViewHolder(private val binding: ItemMoviePosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SearchViewModel.Movie) {
            val fullImageUrl = "https://image.tmdb.org/t/p/w185${movie.poster_path}"
            binding.moviePosterImageView.load(fullImageUrl) {
                //placeholder(R.drawable.placeholder) // 로딩 중 표시할 이미지
                //error(R.drawable.error) // 로딩 실패 시 표시할 이미지
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun submitList(data: List<SearchViewModel.Movie>) {
        movieList = data
        notifyDataSetChanged()
    }
}


