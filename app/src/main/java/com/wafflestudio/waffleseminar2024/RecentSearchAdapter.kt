package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.ItemRecentSearchBinding

class RecentSearchAdapter : ListAdapter<RecentSearch, RecentSearchAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearch: RecentSearch) {
            binding.searchTextView.text = recentSearch.query
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<RecentSearch>() {
        override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean = oldItem == newItem
    }
}
