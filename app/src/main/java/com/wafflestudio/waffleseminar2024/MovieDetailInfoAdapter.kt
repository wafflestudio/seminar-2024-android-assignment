package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieDetailInfoAdapter(private val infoList: List<String>) : RecyclerView.Adapter<MovieDetailInfoAdapter.InfoViewHolder>() {

    inner class InfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val infoTextView: TextView = view.findViewById(R.id.infoTextView)

        fun bind(info: String) {
            infoTextView.text = info
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_info, parent, false)
        return InfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int = infoList.size
}
