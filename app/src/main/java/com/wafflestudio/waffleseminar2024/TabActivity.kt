package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityTabBinding
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding

class TabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        // 페이지에 표시할 데이터를 준비
        val items = listOf("Page 1", "Page 2", "Page 3", "Page 4")
        val tabIcons = listOf(
            R.drawable.ic_game,
            R.drawable.ic_app,
            R.drawable.ic_search,
            R.drawable.ic_book
        )
        val tabTexts = listOf("게임", "앱", "검색", "프로필")

        // 어댑터 생성
        val adapter = TabAdapter(items)
        viewPager.adapter = adapter

        // TabLayout과 ViewPager2를 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTexts[position]
            tab.setIcon(tabIcons[position])
        }.attach()
    }

    private fun addWorkspaceUrl() {
        val slackWorkspaceValueView: TextView = ActivityUserInformationBinding.inflate(layoutInflater).slackWorkspaceValue
        val workspaceUrl = intent.getStringExtra("WORKSPACE_URL")
        slackWorkspaceValueView.text = workspaceUrl

    }
}