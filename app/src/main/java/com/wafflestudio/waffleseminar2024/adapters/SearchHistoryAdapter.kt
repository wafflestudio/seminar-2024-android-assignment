package com.wafflestudio.waffleseminar2024.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.database.SearchQuery

class SearchHistoryAdapter(
//    private var queries: List<SearchQuery>,
    private val onDeleteClick: (SearchQuery) -> Unit,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    private val queries = mutableListOf<SearchQuery>()

    fun updateQueries(newQueries: List<SearchQuery>) {
        val diffResult = DiffUtil.calculateDiff(SearchQueryDiffCallback(queries, newQueries))
        queries.clear()
        queries.addAll(newQueries)
        diffResult.dispatchUpdatesTo(this)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val queryTextView: TextView = itemView.findViewById(R.id.queryTextView)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(searchQuery: SearchQuery) {  // SearchQuery 타입을 받아옴
            queryTextView.text = searchQuery.query  // 객체의 query 필드 사용
            itemView.setOnClickListener {
                onItemClick(searchQuery.query)  // query 값만 전달
            }
            deleteButton.setOnClickListener {
                onDeleteClick(searchQuery) // 삭제 콜백 호출
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(queries[position])
    }

    override fun getItemCount(): Int = queries.size


}
