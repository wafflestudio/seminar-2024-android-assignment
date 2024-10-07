package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityMaintabBinding
import com.wafflestudio.waffleseminar2024.CustomPagerAdapter

class MainTabActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMaintabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaintabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabTitles = arrayOf("게임", "앱", "검색", "프로필")

        // ViewPager2 Adapter 설정
        val pagerAdapter = CustomPagerAdapter()
        binding.viewPager.adapter = pagerAdapter

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}