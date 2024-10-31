package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieDetailGenreAdapter(private val genres: List<Genre>) : RecyclerView.Adapter<MovieDetailGenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val genreTextView: TextView = view.findViewById(R.id.genreTextView)

        fun bind(genre: Genre) {
            genreTextView.text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size
}
