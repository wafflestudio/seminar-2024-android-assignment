package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GenreAdapter(private var genres: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var filteredGenres: List<Genre> = genres

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.genreName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        Log.d("GenreAdapter", "Binding genre: ${filteredGenres[position].name}")
        holder.genreName.text = filteredGenres[position].name
    }

    override fun getItemCount(): Int {
        Log.d("GenreAdapter", "Item count: ${filteredGenres.size}")
        return filteredGenres.size
    }

    fun updateGenres(newGenres: List<Genre>) {
        genres = newGenres
        filteredGenres = newGenres
        notifyDataSetChanged()
        Log.d("GenreAdapter", "Genres updated, new size: ${filteredGenres.size}")
    }
}
