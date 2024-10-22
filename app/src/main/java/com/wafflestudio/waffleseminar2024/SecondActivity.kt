package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SecondActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        // ViewPager2와 TabLayout 초기화
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // PagerAdapter 연결
        adapter = PagerAdapter(this)
        viewPager.adapter = adapter

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab 1"
                    tab.setIcon(R.drawable.ic_tab1)
                }
                1 -> {
                    tab.text = "Tab 2"
                    tab.setIcon(R.drawable.ic_tab2)
                }
                2 -> {
                    tab.text = "Search"
                    tab.setIcon(R.drawable.ic_tab3)
                }
                3 -> {
                    tab.text = "User Info"
                    tab.setIcon(R.drawable.ic_tab4)
                }
            }
        }.attach()
    }
}
