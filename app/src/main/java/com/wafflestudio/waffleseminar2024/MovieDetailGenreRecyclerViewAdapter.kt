package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MovieDetailGenreRecyclerViewAdapter(private val genreData: List<com.wafflestudio.waffleseminar2024.data.Genre>?): RecyclerView.Adapter<MovieDetailGenreRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_detail_genre_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = genreData!![position]
        holder.textView.text = currentItem.name
    }

    override fun getItemCount(): Int {
        Log.d("a", genreData!!.size.toString())
        return genreData!!.size
    }
}