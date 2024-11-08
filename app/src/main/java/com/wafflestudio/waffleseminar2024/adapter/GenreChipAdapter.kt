package com.wafflestudio.waffleseminar2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.R

class GenreChipAdapter(private val genres: List<Genre>) : RecyclerView.Adapter<GenreChipAdapter.GenreViewHolder>() {

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreTextView: TextView = itemView.findViewById(R.id.genreChip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genrechip_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.genreTextView.text = genre.name
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}