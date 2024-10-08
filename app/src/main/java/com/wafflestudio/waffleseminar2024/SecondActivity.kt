package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SecondActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        // Adapter 설정
        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // 각 탭에 아이콘 및 텍스트 설정
            when (position) {
                0 -> {
                    tab.text = "탭 1"
                    tab.setIcon(R.drawable.ic_tab1) // 아이콘 리소스
                }
                1 -> {
                    tab.text = "탭 2"
                    tab.setIcon(R.drawable.ic_tab2) // 아이콘 리소스
                }
                2 -> {
                    tab.text = "탭 3"
                    tab.setIcon(R.drawable.ic_tab3) // 아이콘 리소스
                }
                3 -> {
                    tab.text = "탭 4"
                    tab.setIcon(R.drawable.ic_tab4) // 아이콘 리소스
                }
            }
        }.attach()
        // 탭 클릭 시 애니메이션 추가
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // 애니메이션 적용
                tab.view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(150).withEndAction {
                    tab.view.animate().scaleX(1f).scaleY(1f).setDuration(150).start()
                }.start()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })



//        // TabLayout과 ViewPager2 연동
//        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
//            tab.text = "Tab ${position+1}"
//        }.attach()



    }

}