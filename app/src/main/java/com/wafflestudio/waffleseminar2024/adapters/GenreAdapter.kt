package com.wafflestudio.waffleseminar2024.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.data.Genre
import com.wafflestudio.waffleseminar2024.R


class GenreAdapter(
    private var genreList: List<Genre>,
    private var iconList: List<Int>,
    private val onGenreClick:(Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var showMovies = false // 장르와 영화 전환 플래그

    fun showGenres() {
        showMovies = false
        //notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreViewHolder -> {
                val genre = genreList[position]
                val icon = iconList[position]
                holder.bind(genre,icon)
            }
        }
    }


    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreName: TextView = itemView.findViewById(R.id.genreName)
        private val genreIcon: ImageView = itemView.findViewById(R.id.icon)
        fun bind(genre: Genre, icon: Int) {
            genreName.text = genre.name
            genreIcon.setImageResource(icon)
            itemView.setOnClickListener{
                onGenreClick(genre.id)
            }
        }
    }
}
