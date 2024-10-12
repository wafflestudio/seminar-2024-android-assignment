package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding
import org.w3c.dom.Text

class ViewPagerAdapter(private val items: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    class GameViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.PageGame)
    }
    class AppViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.PageApp)
    }
    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.PageSearch)
    }

    class ProfileViewHolder(private val binding: ActivityUserInformationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return when(viewType){
            0 -> {
                val gameView = LayoutInflater.from(parent.context).inflate(R.layout.page_game, parent, false)
                GameViewHolder(gameView)
            }
            1 -> {
                val appView = LayoutInflater.from(parent.context).inflate(R.layout.page_app, parent, false)
                AppViewHolder(appView)
            }
            2 -> {
                val searchView = LayoutInflater.from(parent.context).inflate(R.layout.page_search, parent, false)
                SearchViewHolder(searchView)
            }
            else -> {
                val binding = ActivityUserInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProfileViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
