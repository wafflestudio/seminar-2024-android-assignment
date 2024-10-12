package com.wafflestudio.waffleseminar2024

import android.content.Intent
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
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityHomeBinding


class HomeActivity: FragmentActivity() {
    private lateinit var homeBinding: ActivityHomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        val viewPager: ViewPager2 = homeBinding.viewPager

        val tabLayout: TabLayout = homeBinding.tabLayout
        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0->{
                    tab.text = "게임"
                    tab.setIcon(R.drawable.game)
                }
                1->{
                    tab.text = "앱"
                    tab.setIcon(R.drawable.app)
                }
                2->{
                    tab.text = "검색"
                    tab.setIcon(R.drawable.search)
                }
                else->{
                    tab.text = "프로필"
                    tab.setIcon(R.drawable.profile)
                }
            }
        }.attach()
    }
}