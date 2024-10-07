package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.ItemButtonBinding

class SearchButtonGridAdapter : RecyclerView.Adapter<SearchButtonGridAdapter.ButtonViewHolder>() {

    private var genreList: List<SearchViewModel.Genre> = emptyList()

    // ViewHolder 정의
    class ButtonViewHolder(private val binding: ItemButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: SearchViewModel.Genre) {
            binding.button.text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun submitList(data: List<SearchViewModel.Genre>) {
        genreList = data
        notifyDataSetChanged()
    }
}

