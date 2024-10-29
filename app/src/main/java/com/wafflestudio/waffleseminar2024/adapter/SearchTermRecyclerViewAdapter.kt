package com.wafflestudio.waffleseminar2024.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R

class searchTermRecyclerViewAdapter(private var searchTerms: List<String>) : RecyclerView.Adapter<searchTermRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(term: String) {
            itemView.findViewById<TextView>(R.id.text_search_term).text = term
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searchterm_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchTerms[position])
    }

    fun updateSearchTerms(newSearchTerms: List<String>) {
        searchTerms = newSearchTerms
        notifyDataSetChanged() // 데이터가 변경되었음을 알림
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "Item count: ${searchTerms.size}") // 추가된 로그
        return searchTerms.size
    }
}