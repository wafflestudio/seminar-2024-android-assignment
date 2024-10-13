package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.viewPagerFragments.OnGenreClickListener

class GenreRecyclerViewAdapter(private val mData: List<Genre>, private val listener: OnGenreClickListener) : RecyclerView.Adapter<GenreRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genre_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mData[position]
        holder.textView.text = currentItem.name
        holder.textView.setOnClickListener {
            listener.onGenreClick(currentItem.id)
        }
    }

    override fun getItemCount(): Int {
        Log.d("size", mData.size.toString())
        return mData.size
    }
}
