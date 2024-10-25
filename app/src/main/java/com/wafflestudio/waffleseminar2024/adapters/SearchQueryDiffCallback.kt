package com.wafflestudio.waffleseminar2024.adapters

import androidx.recyclerview.widget.DiffUtil
import com.wafflestudio.waffleseminar2024.database.SearchQuery

class SearchQueryDiffCallback(
    private val oldList: List<SearchQuery>,
    private val newList: List<SearchQuery>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
