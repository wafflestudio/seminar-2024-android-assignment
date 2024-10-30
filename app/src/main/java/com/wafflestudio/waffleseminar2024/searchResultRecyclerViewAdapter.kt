package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.data.MovieEntity
import com.wafflestudio.waffleseminar2024.viewPagerFragments.OnMovieClickListener

class searchResultRecyclerViewAdapter(private val movieList: List<MovieEntity>, private val listener: OnMovieClickListener) : RecyclerView.Adapter<searchResultRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = movieList[position]
        val imageUrl = "https://image.tmdb.org/t/p/w185" + currentItem.poster_path
        holder.imageView.load(imageUrl)
        holder.imageView.setOnClickListener {
            listener.onMovieClick(currentItem.id!!)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
