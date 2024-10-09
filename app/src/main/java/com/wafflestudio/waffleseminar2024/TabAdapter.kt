package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TabAdapter(
    private val activity: AppCompatActivity,
    private val searchViewModel: SearchViewModel
) : RecyclerView.Adapter<TabAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_page, parent, false)
        Log.d("tab adapter", "create view holder")
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        when (position) {
            0 -> holder.bindText("page1")
            1 -> holder.bindText("page2")
            2 -> holder.bindSearchTab()
            3 -> holder.bindText("profile")
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)
        private val searchView: androidx.appcompat.widget.SearchView = view.findViewById(R.id.searchView)
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        fun bindText(text: String) {
            textView.visibility = View.VISIBLE
            textView.text = text
            searchView.visibility = View.GONE
            recyclerView.visibility = View.GONE
        }

        fun bindSearchTab() {
            textView.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE

            recyclerView.layoutManager = GridLayoutManager(itemView.context, 2)
            val adapter = GenreAdapter(listOf())
            recyclerView.adapter = adapter

            searchViewModel.genres.observe(activity, Observer { genres ->
                Log.d("TabAdapter", "Genres received: ${genres.size}")
                adapter.updateGenres(genres)
                Log.d("TabAdapter", "Adapter updated with new genres")
            })
        }
    }
}
