package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding
import com.wafflestudio.waffleseminar2024.databinding.ItemMovieInfoBinding

class MovieDetailInfoAdapter(private val infoList: List<Pair<String, String>>) : RecyclerView.Adapter<MovieDetailInfoAdapter.InfoViewHolder>() {

    private var _binding: ItemMovieInfoBinding? = null
    private val binding get() = _binding!!

    inner class InfoViewHolder(private val binding: ItemMovieInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String, info: String) {
            binding.infoTitle.text = title
            binding.infoTextView.text = info
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemMovieInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(infoList[position].first, infoList[position].second)
    }

    override fun getItemCount(): Int = infoList.size

}
