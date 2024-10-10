package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityTabBinding
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding

class TabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private var workspaceUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workspaceUrl = intent.getStringExtra("WORKSPACE_URL")

        Log.d("tab activity", "binding correct")

        //searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val tabIcons = listOf(
            R.drawable.ic_game,
            R.drawable.ic_app,
            R.drawable.ic_search,
            R.drawable.ic_book
        )
        val tabTexts = listOf("게임", "앱", "검색", "프로필")

        val adapter = TabAdapter(this, searchViewModel, workspaceUrl)
        Log.d("tab activity", "adapter correct")

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTexts[position]
            tab.setIcon(tabIcons[position])
        }.attach()
    }
}