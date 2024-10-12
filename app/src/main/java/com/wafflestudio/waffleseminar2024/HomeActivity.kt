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
import com.wafflestudio.waffleseminar2024.databinding.HomeBinding

class Home : FragmentActivity() {
    private lateinit var homeBinding: HomeBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homeBinding = HomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        val viewPager = homeBinding.viewPager
        val tabLayout = homeBinding.tabLayout

        val adapter = ViewPager2Adapter(this)
        viewPager.adapter = adapter


    }
}