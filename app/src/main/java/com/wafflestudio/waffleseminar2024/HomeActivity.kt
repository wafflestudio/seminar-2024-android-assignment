package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityHomeBinding


class HomeActivity: FragmentActivity() {
    private lateinit var homeBinding: ActivityHomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        val viewPager: ViewPager2 = homeBinding.viewPager
        val items = listOf("Page 1", "Page 2", "Page 3", "Profile")

        val tabLayout: TabLayout = homeBinding.tabLayout
        viewPager.adapter = ViewPagerAdapter(items)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }
}