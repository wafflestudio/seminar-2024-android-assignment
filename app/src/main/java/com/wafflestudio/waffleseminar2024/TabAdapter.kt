package com.wafflestudio.waffleseminar2024

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding

class TabAdapter(
    private val items: List<String>,
    private val activity: TabActivity
) : RecyclerView.Adapter<TabAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = when (viewType) {
            3 -> inflater.inflate(
                R.layout.activity_user_information,
                parent,
                false
            )
            else -> inflater.inflate(
                R.layout.item_viewpager_page,
                parent,
                false
            )
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TabAdapter", "onBindViewHolder called for position: $position")
        when (position) {
            3 -> {
                activity.addToolbarOption()
                activity.addWorkspaceUrl()
                activity.addGithubLink()
            }

            else -> holder.textView.text = items[position]
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}