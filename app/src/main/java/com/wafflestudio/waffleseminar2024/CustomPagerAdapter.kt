package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class CustomPagerAdapter : RecyclerView.Adapter<CustomPagerAdapter.PageViewHolder>() {

    private val pages = listOf("Game Page", "App Page", "Search Page", "Profile Page")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        view.layoutParams = layoutParams
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.textView.text = pages[position]
    }

    override fun getItemCount(): Int = pages.size

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(android.R.id.text1)
    }
}
